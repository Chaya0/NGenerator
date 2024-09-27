export enum AttributeFieldType {
    INPUT = "INPUT",
    DROPDOWN = "DROPDOWN",
    TREE_SELECT = "TREE_SELECT",
    PICK_LIST = "PICK_LIST",
    CALENDAR = "CALENDAR",
    CHECKBOX = "CHECKBOX",
  }
  
  export class SearchField {
    type: AttributeFieldType;
    _multiSelect: boolean;
    _maxLength: number | Date | null;
    _minLength: number | Date | null;
    _required: boolean;
  
    constructor(type: AttributeFieldType = AttributeFieldType.INPUT, multiSelect: boolean = false, maxLength: number | Date | null = null, minLength: number | Date | null = null, required: boolean = false) {
      this.type = type;
      this._multiSelect = multiSelect;
      this._maxLength = maxLength;
      this._minLength = minLength;
      this._required = required;
    }
  
    static INPUT(): SearchField {
      return new SearchField();
    }
  
    static DROPDOWN(): SearchField {
      return new SearchField(AttributeFieldType.DROPDOWN);
    }
  
    static PICK_LIST(): SearchField {
      return new SearchField(AttributeFieldType.PICK_LIST, true);
    }
  
    static TREE_SELECT(): SearchField {
      return new SearchField(AttributeFieldType.TREE_SELECT, true);
    }
  
    static CALENDAR() {
      return new SearchField(AttributeFieldType.CALENDAR);
    }
  
    static CHECKBOX() {
      return new SearchField(AttributeFieldType.CHECKBOX);
    }
  
    maxLength(value: any) {
      this._maxLength = value;
      return this;
    }
  
    minLength(value: any) {
      this._minLength = value;
      return this;
    }
  
    multiSelect(value: boolean) {
      this._multiSelect = value;
      return this;
    }
  
    required(value: boolean) {
      this._required = value;
      return this;
    }
  
    getMaxLengthDate() {
      return this._maxLength as Date;
    }
  
    getMinLengthDate() {
      return this._minLength as Date;
    }
  
    isPickList() {
      return this.type === AttributeFieldType.PICK_LIST;
    }
  
    isTreeSelect() {
      return this.type === AttributeFieldType.TREE_SELECT;
    }
  
    isDropdown() {
      return this.type === AttributeFieldType.DROPDOWN;
    }
  
    isCheckbox() {
      return this.type === AttributeFieldType.CHECKBOX;
    }
  
    isCalendar() {
      return this.type === AttributeFieldType.CALENDAR;
    }
  
    isInput() {
      return this.type === AttributeFieldType.INPUT;
    }
  }
  