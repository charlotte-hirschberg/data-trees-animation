package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Date: Sept. 10, 2016
 * @Description: From Intro to Java, 10th ed., by Daniel Liang
 * Extended by RBTree, AVL, and BST classes.
 */

//Begin Class AbstractTree
public abstract class AbstractTree<E> implements Tree<E> {
  @Override /** Inorder traversal from the root*/
  public void inorder() {
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
  }

  @Override /** Return true if the tree is empty */
  public boolean isEmpty() {
    return getSize() == 0;
  }
}
//End Class AbstractTree