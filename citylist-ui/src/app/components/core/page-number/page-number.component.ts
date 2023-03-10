import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-page-number',
  templateUrl: './page-number.component.html',
  styleUrls: ['./page-number.component.css']
})
export class PageNumberComponent  implements OnInit {

  @Input() isPageFull: number = 1000;
  @Input() pageNum!: number;
  @Output() pageNumChange = new EventEmitter();

  @Output() refreshEmitter = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  changePage(pn: string) {
    if (pn === 'p') {
      if (this.pageNum < 1) {
        return;
      }
      this.pageNum--;
    } else if (pn === 'n') {
      if (this.isPageFull) {
        this.pageNum++;
      } else {
        return;
      }
    } else if (pn === 'f') {
      this.pageNum = 0;
    } else {
      return;
    }

    this.pageNumChange.emit(this.pageNum);
    this.refreshEmitter.emit();
  }
}
