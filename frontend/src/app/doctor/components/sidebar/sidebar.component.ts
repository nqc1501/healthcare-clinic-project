import { CollectionViewer, DataSource, SelectionChange } from '@angular/cdk/collections';
import { FlatTreeControl } from '@angular/cdk/tree';
import { CommonModule } from '@angular/common';
import { Component, Injectable } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTreeModule } from '@angular/material/tree';
import { Router, RouterOutlet } from '@angular/router';
import { BehaviorSubject, Observable, merge } from 'rxjs';
import { map } from 'rxjs/operators';

export class DynamicFlatNode {

  constructor (
    public item: string,
    public link: string,
    public level = 1,
    public expandable = false
  ) {}
}

@Injectable({providedIn: 'root'})
export class DynamicDatabase {
  dataMap = new Map<string, string[]>([
    ['Công việc', ['Đăng ký lịch làm việc', 'Lịch làm việc']],
  ]);

  linkMap = new Map<string, string>([
    ['Trang chủ', '/doctor/dashboard'],
    ['Đăng ký lịch làm việc', '/doctor/register-schedule'],
    ['Lịch làm việc', '/doctor/work-schedule'],
    ['Danh sách bệnh nhân', '/doctor/list-patient'],
  ]);

  rootLevelNodes: string[] = [
    'Trang chủ',
    'Công việc',
    'Danh sách bệnh nhân',
  ];

  initialData(): DynamicFlatNode[] {
    return this.rootLevelNodes.map(name => {
      const hasChildren = this.isExpandable(name);
      const link = this.linkMap.get(name) || '';
      return new DynamicFlatNode(name, link, 0, hasChildren);
    });
  }

  getChildren(node: string): string[] | undefined {
    return this.dataMap.get(node);
  }

  isExpandable(node: string): boolean {
    return this.dataMap.has(node);
  }
}

export class DynamicDataSource implements DataSource<DynamicFlatNode> {

  dataChange = new BehaviorSubject<DynamicFlatNode[]>([]);

  get data(): DynamicFlatNode[] {
    return this.dataChange.value;
  }

  set data(value: DynamicFlatNode[]) {
    this._treeControl.dataNodes = value;
    this.dataChange.next(value);
  }

  constructor(
    private _treeControl: FlatTreeControl<DynamicFlatNode>,
    private _database: DynamicDatabase
  ) {}

  connect(collectionViewer: CollectionViewer): Observable<DynamicFlatNode[]> {
    this._treeControl.expansionModel.changed.subscribe(change => {
      if (
        (change as SelectionChange<DynamicFlatNode>).added || 
        (change as SelectionChange<DynamicFlatNode>).removed
      ) {
        this.handleTreeControl(change as SelectionChange<DynamicFlatNode>);
      }
    });

    return merge(collectionViewer.viewChange, this.dataChange).pipe(map(() => this.data));
  }

  disconnect(collectionViewer: CollectionViewer): void {}

  handleTreeControl(change: SelectionChange<DynamicFlatNode>) {
    if (change.added) {
      change.added.forEach(node => this.toggleNode(node, true));
    }

    if (change.removed) {
      change.removed
        .slice()
        .reverse()
        .forEach(node => this.toggleNode(node, false));
    }
  }

  toggleNode(node: DynamicFlatNode, expand: boolean) {
    const children = this._database.getChildren(node.item);
    const index = this.data.indexOf(node);
    if (!children || index < 0) {
      return;
    }

    if (!children.length) {
      if (expand) {
        node.link = this._database.linkMap.get(node.item);
      } else {
        node.link = '';
      }
    }

    if (expand) {
      const nodes = children.map(child => {
        const hasChildren = this._database.isExpandable(child);
        const link = hasChildren ? '' : (this._database.linkMap.get(child) || '');
        return new DynamicFlatNode(child, link, node.level + 1, hasChildren);
      }
      );
      this.data.splice(index + 1, 0, ...nodes);
    } else {
      let count = 0;
      for (
        let i = index + 1;
        i < this.data.length && this.data[i].level > node.level;
        i++, count++
      ) {}
      this.data.splice(index + 1, count);
    }

    this.dataChange.next(this.data);
  }
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    CommonModule,
    MatSidenavModule,
    MatDividerModule,
    MatListModule,
    MatTreeModule,
    MatButtonModule,
    MatIconModule,
    RouterOutlet
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  treeControl: FlatTreeControl<DynamicFlatNode>;

  dataSource: DynamicDataSource;

  getLevel = (node: DynamicFlatNode) => node.level;

  isExpandable = (node: DynamicFlatNode) => node.expandable;

  hasChild = (_: number, _noData: DynamicFlatNode) => _noData.expandable;

  constructor(
    database: DynamicDatabase,
    private router: Router
  ) {
    this.treeControl = new FlatTreeControl<DynamicFlatNode>(this.getLevel, this.isExpandable);
    this.dataSource = new DynamicDataSource(this.treeControl, database);

    this.dataSource.data = database.initialData();
  }

  ngOnInit() {

  }

  nodeClicked(node: DynamicFlatNode) {
    this.router.navigateByUrl(node.link);
  }

}
