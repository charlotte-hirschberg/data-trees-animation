package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Created Date: Sept. 10, 2016
 * @Description: Extension of TreeView Pane. Overrides displayTree() methods to determine an RBTree node's color
 */

//Imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

//Begin Class TreeView
public class RBTreeView extends TreeView {
    private RBTree<Integer> tree = new RBTree<>();

    public RBTreeView(BST<Integer> tree) {
        super(tree);
        this.tree = (RBTree<Integer>)tree;
    }
    
    /**
     *
     * @param msg
     */
    @Override
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
  private void displayTree(RBTree.TreeNode<Integer> root,
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
    
    tree.getRed(root);
    
    // Display a node in a circle
    Circle circle = new Circle(x, y, radius);
    circle.setStroke(Color.BLACK);
    if(tree.getRed(root))
    {
        circle.setFill(Color.RED);
    }
    else 
    {
        circle.setFill(Color.GRAY);
    }

    getChildren().addAll(circle,
      new Text(x - 10, y + 4, root.element + ""));
  }
}
//End Class TreeView