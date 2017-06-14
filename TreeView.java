package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Date: Sept. 10, 2016
 * @Description: Sourced from Intro to Java, 10th ed., Daniel Liang. Creates a Pane that displays the nodes of a binary
 *          tree connected by lines and accompanied by a status message like "Node inserted".
 *          Extended by RBTreeView
 */

//Imports
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

//Begin Class TreeView
public class TreeView extends Pane {
  protected BST<Integer> tree = new BST<>();
  protected double radius = 15; // Tree node radius
  protected double vGap = 50; // Gap between two levels in a tree
  protected Text status;

  //Constructor
  TreeView(BST<Integer> tree) {
    this.status = new Text(20, 20, "Tree is empty");
    this.tree = tree;
    getChildren().add(status);
  }

  //Display a message at the top right
  public void setStatus(String msg) {
    status.setText(msg);
    getChildren().add(status);
  }
  
  // Display a message without clearing the pane
  public void replaceStatus(String msg)
  {
      status.setText(msg);
  }

  public void displayTree(String msg) {
    this.getChildren().clear(); // Clear the pane
    setStatus(msg);
    if (tree.getRoot() != null) {
      // Display tree recursively    
      displayTree(tree.getRoot(), getWidth() / 2, vGap,
        getWidth() / 4);
    }
  }

  /** Display a subtree rooted at position (x, y) */
  private void displayTree(BST.TreeNode<Integer> root,
      double x, double y, double hGap) {
    if (root.left != null) {
      // Draw a line to the left node
      getChildren().add(new Line(x - hGap, y + vGap, x, y));
      // Draw the left subtree recursively
      displayTree(root.left, x - hGap, y + vGap, hGap / 2);
    }
    
    //right subtree:
    if (root.right != null) {
      // Draw a line to the right node
      getChildren().add(new Line(x + hGap, y + vGap, x, y));
      // Draw the right subtree recursively
      displayTree(root.right, x + hGap, y + vGap, hGap / 2);
    }
    
    // Display a node in a circle
    Circle circle = new Circle(x, y, radius);
    circle.setFill(Color.web("#cccc00"));
    circle.setStroke(Color.BLACK);
    getChildren().addAll(circle,
      new Text(x - 10, y + 4, root.element + ""));
  }
}
//End Class TreeView