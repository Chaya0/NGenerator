import {Attribute} from "../../core/entity-utils/attribute";
import {Validators} from "@angular/forms";
import {TreeNode} from "primeng/api";
import {AppUtils} from "./app-utils";

export class FormUtils {
  static getValidators(attr: Attribute) {
    const validators = [];
    if (!attr.nullable) {
      validators.push(Validators.required);
    }
    if (attr.maxLength) {
      validators.push(Validators.maxLength(attr.maxLength));
    }
    if (attr.minLength) {
      validators.push(Validators.minLength(attr.minLength));
    }
    return validators;
  }


  public static convertDataToTreeNodes(data: any[], attr: Attribute, attributes: Attribute[], expanded: boolean = false, colored: boolean = false): TreeNode[] {
    const nodeMap: { [key: number]: TreeNode } = {};
    const treeNodes: TreeNode[] = [];

    // Check if the attribute has potential for a tree structure
    const attrType = AppUtils.getAttributeType(attr.attr_name, attributes);
    const structure = AppUtils.getFkAttributeStructure(attr.attr_name, attributes);

    if (!structure) {
      return treeNodes;
    }

    // We need to find the self referenced attribute to start with tree creation, because only entities that contain a reference to itself can be structured like a tree.
    const selfReferencedAttribute: Attribute | undefined = structure.attributes.find((attr) => attr.type === structure.entityName);

    if (!selfReferencedAttribute) {
      return treeNodes;
    }

    // Proceed with tree structure creation
    data.forEach((value) => {
      nodeMap[value.id] = {
        label: AppUtils.getEntityViewValue(value, attrType, " "),
        data: value,
        expanded: expanded,
        type: attrType,
        children: [],
      };
    });

    data.forEach((value) => {
      const parentId = value[selfReferencedAttribute.attr_name]?.id;
      if (parentId) {
        nodeMap[parentId].children?.push(nodeMap[value.id]);
      } else {
        treeNodes.push(nodeMap[value.id]);
      }
    });

    treeNodes.forEach(node => assignColors(node, 0, colored));
    return treeNodes;
  }
}

const colorClasses = [
  'text-primary font-bold',
  'text-indigo-700 font-bold',
  'text-indigo-600 font-bold',
  'text-indigo-500 font-bold',
  'text-indigo-400 font-bold',
  'text-indigo-300 font-bold',
];

// Helper function to assign colors recursively
const assignColors = (node: TreeNode, level: number, colored: boolean) => {
  if (colored) {
    node.styleClass = colorClasses[level % colorClasses.length];
  }
  node.children?.forEach(child => assignColors(child, level + 1, colored));
};
