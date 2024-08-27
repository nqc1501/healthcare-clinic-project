import { Component, Injectable } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { Router, RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTreeModule } from '@angular/material/tree';
import { MatButtonModule } from '@angular/material/button';
import { CollectionViewer, DataSource, SelectionChange } from '@angular/cdk/collections';
import { BehaviorSubject, Observable, merge } from 'rxjs';
import { FlatTreeControl } from '@angular/cdk/tree';
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
    ['Quản lý bác sỹ', ['Danh sách bác sỹ']],
    ['Quản lý bệnh nhân', ['Danh sách đang chờ', 'Danh sách đã khám']],
    ['Quản lý phòng khám', ['Danh sách phòng khám']],
    ['Quản lý vật tư', ['Danh sách thuốc', 'Danh sách thiết bị']],
  ]);

  linkMap = new Map<string, string>([
    ['Trang chủ', '/admin/dashboard'],
    ['Danh sách bác sỹ', '/admin/list-doctor'],
    ['Danh sách đang chờ', '/admin/list-patient/waiting'],
    ['Danh sách đã khám', '/admin/list-patient/completed'],
    ['Danh sách phòng khám', '/admin/list-room'],
    ['Danh sách thuốc', 'admin/list-medication'],
    ['Danh sách thiết bị', 'admin/list-supply'],
    ['Quản lý ca làm việc', '/admin/list-shift'],
    ['Quản lý đăng ký lịch', '/admin/registry-schedule-management'],
    ['Quản lý hẹn khám', '/admin/list-appointment'],
  ]);

  rootLevelNodes: string[] = [
    'Trang chủ',
    'Quản lý bác sỹ', 
    'Quản lý bệnh nhân', 
    'Quản lý phòng khám',
    'Quản lý vật tư',
    'Quản lý ca làm việc',
    'Quản lý đăng ký lịch',
    'Quản lý hẹn khám'
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
    MatDividerModule,
    MatSidenavModule,
    MatListModule,
    RouterModule,
    MatIconModule,
    MatExpansionModule,
    MatTreeModule,
    MatButtonModule
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
    private router: Router,
    database: DynamicDatabase,
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
