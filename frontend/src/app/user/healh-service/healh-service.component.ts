import { Component } from '@angular/core';

@Component({
  selector: 'app-healh-service',
  standalone: true,
  imports: [],
  templateUrl: './healh-service.component.html',
  styleUrl: './healh-service.component.css'
})
export class HealhServiceComponent {

  constructor() { }

  ngOnInit() {
    console.log('hello');
  }

}
