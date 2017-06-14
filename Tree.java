package chirschberger_week2;

/**
 * @param <E>
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Date: Sept. 10, 2016
 * @Description: Interface extended by AbstractTree; sourced from Intro to Java, 10th ed., Daniel Liang
 */


/*Begin Interface Tree*/
public interface Tree<E> extends Iterable<E> {
  /** Return true if the element is in the tree
     * @param e
     * @return  */
  public boolean search(E e);

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e);

  /** Delete the specified element from the tree
   * Return true if the element is deleted successfully */
  public boolean delete(E e);

  /** Inorder traversal from the root*/
  public void inorder();

  /** Postorder traversal from the root */
  public void postorder();

  /** Preorder traversal from the root */
  public void preorder();

  /** Get the number of nodes in the tree */
  public int getSize();

  /** Return true if the tree is empty */
  public boolean isEmpty();
}
//End Interface Tree