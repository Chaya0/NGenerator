import {entities} from "../../features/entities/entities";
import * as Enums from "../../features/entities/enums.model";
import {Attribute} from "../../core/entity-utils/attribute";
import {Structure} from "../../features/entities/structure";

export class AppUtils {
  /**
   * Converts the first letter of the given string to lowercase.
   * @param str - The string to convert.
   * @returns The string with the first letter in lowercase.
   */
  public static toFirstLetterLowercase(str: string | undefined): string {
    if (!str) return "";
    return str.charAt(0).toLowerCase() + str.slice(1);
  }

  /**
   * Checks if a variable is a foreign key.
   * @param type - Type of the variable.
   * @returns true if it is a foreign key.
   */
  public static isForeignKey(type: string): boolean {
    return entities.filter(e => e.name === type).length > 0;
  }

  /**
   * Checks if a variable is a string or date.
   * @param value - Type of the variable.
   * @returns true if it is a string or date.
   */
  public static isRegularInput(value: string): boolean {
    return value === 'string' || value === 'number';
  }

  /**
   * Checks if a variable is a date.
   * @param value - Type of the variable.
   * @returns true if it is a date.
   */
  public static isDateInput(value: string): boolean {
    return value === 'date';
  }


  /**
   * //TODO UNUSED
   * Checks if a variable is a date range
   * @param value - Type of the variable.
   * @returns true if it is a date range.
   */
  public static isDateRangeInput(value: string): boolean {
    return value === 'datetime-local';
  }

  /**
   * Checks if a variable is a date.
   * @param value - Type of the variable.
   * @returns true if it is a string or date.
   */
  public static isDateTimeInput(value: string): boolean {
    return value === 'datetime-local';
  }

  /**
   * Checks if a variable is a boolean.
   * @param value - Type of the variable.
   * @returns true if it is a boolean.
   */
  public static isBoolean(value: string): boolean {
    return value === 'boolean';
  }

  /**
   * Checks if a variable is a enum.
   * @param value - Type of the variable.
   * @returns true if it is a enum.
   */
  public static isEnum(value: string): boolean {
    return value === 'enum';
  }


  /**
   * Checks if an attribute is a foreign key by attribute's name.
   * @param name - name of the attribute.
   * @param attributes - attribute list.
   * @returns true if it is a foreign key.
   */
  public static isAttributeForeignKey(name: string, attributes: Attribute[]): boolean {
    return AppUtils.isForeignKey(this.getAttributeType(name, attributes));
  }

  public static getAttributeType(name: string, attributes: Attribute[]): string {
    return attributes.filter(e => e.attr_name === name)[0].type;
  }

  public static getFkAttributeStructure(name: string, attributes: Attribute[]): Structure {
    const type = attributes.filter(e => e.attr_name === name)[0].type;
    return entities.filter(e => e.name === type)[0].structure;
  }

  /**
   * @param type Entity for which we want to find search attribute.
   * @returns name of the attribute to be displayed instead of whole object.
   */
  public static getFkSearchAttribute(type: string): string[] {
    return entities.filter(e => e.name === type)[0].structure.fkSearchAttribute;
  }

  public static getDisplayValue(object: any, type: string) {
    if (!object) return null;
    let keys = AppUtils.getFkSearchAttribute(type);
    let displayValue: string | null = null;
    for (let key of keys) {
      if (!object[key] || object[key] === '') continue;
      if (!displayValue) displayValue = "";
      displayValue = displayValue + " " + object[key];
    }
    return displayValue;
  }

  public static getEntityViewValue(obj: any, type: string, delimiter: string = " "): string {
    const keys = this.getFkSearchAttribute(type);
    return keys
      .map(key => obj[key]?.trim())
      .filter(value => value)
      .join(delimiter);
  }

  public static findEnumByName(enumName: string): any {
    return Enums[enumName as keyof typeof Enums];
  }

  public static capitalizeFirstLetter(input: string): string {
    if (!input) return input; // Return the input as is if it's an empty string or undefined/null
    return input.charAt(0).toUpperCase() + input.slice(1);
  }
}
