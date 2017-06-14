package chirschberger_week2;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Charlotte Hirschberger
 * @Assignment Name: chirschberger_week2
 * @Date: Sep 8, 2016
 * @Description: Includes Tab Pane with three tabs- one that displays a binary tree after the first use of Insert and 
 *      another two that display an AVL tree and Red-Black Tree, with the same nodes, after the user clicks Balance.
 *      Integers input in the textfield become nodes ('keys') in the tree.
 */

//Imports
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//Begin Class CHirschberger_Week2
public class CHirschberger_Week2 extends Application {
    private TextField tfKey = new TextField(); // For user's integer entry
    private Label valMsg = new Label(""); // Error message
    private ArrayList<Button> btns = new ArrayList(); // Holds buttons for easy access by handler
    private ArrayList<Integer> keys = new ArrayList();//tree nodes
    
    private BST<Integer> bTree = new BST(); 
    private AVL<Integer> avlTree = new AVL();
    private RBTree <Integer> rbTree = new RBTree();    
    
    private TreeView bView = new TreeView(bTree);//pane for displaying BST
    private TreeView avlView = new TreeView(avlTree);//pane for displaying AVL tree
    private RBTreeView rbView = new RBTreeView(rbTree);// pane for RB Tree
    
    private boolean needsBalance = false; // prevents unnecessary reconstruction of a tree that was just balanced

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        
        BorderPane bPane = new BorderPane();
        bPane.setPadding(new Insets(30, 30, 10, 30));
        bPane.setMinSize(900, 500);
        bPane.setId("bPane");
        
        Scene scene = new Scene(bPane);
        //CSS
        scene.getStylesheets().add(CHirschberger_Week2.class.getResource("TreesStyle.css").toExternalForm());
        primaryStage.setTitle("Tree Structures");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        bPane.setCenter(getBorderCenter());
        bPane.setBottom(getBorderBottom());
        tfKey.requestFocus();
    }
    
    /**
     * Constructs a TabPane with three tabs, each of which displays a Pane with a tree visualization
     * @return TabPane
     */
    private TabPane getBorderCenter()
    {
        TabPane tPane = new TabPane();
        tPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab bstTab = new Tab("Binary Search");
        Tab avlTab = new Tab("AVL Tree");
        Tab rbTab = new Tab("Red-Black Tree");
        
        bstTab.setContent(bView);
        avlTab.setContent(avlView);
        rbTab.setContent(rbView);
                
        tPane.getTabs().addAll(bstTab, avlTab, rbTab);
        
        return tPane;
    }
    
    /**
     * 7x2 GridPane holds TextField and Buttons Insert, Delete, Balance, Clear, Exit
     * @return GridPane
     */
    private GridPane getBorderBottom()
    {        
        String[] btnLbls = {"Insert", "Delete", "Balance", "Clear"};
        
        GridPane gp = new GridPane();
            gp.setHgap(10);
            gp.setAlignment(Pos.CENTER);
            gp.setPadding(new Insets(20, 0, 0, 0));
        
            tfKey.setPrefWidth(75);
            tfKey.setAlignment(Pos.BASELINE_RIGHT);
        
        gp.add(new Label("Enter a key: "), 0, 0);//Row 1, Col 1
        gp.add(tfKey, 1, 0);//Row 1, Col2
        
        //Row 1, Col 3-6
        for(int i = 0; i < btnLbls.length; i++)
        {
            Button b = new Button(btnLbls[i]);
            b.setOnAction(new ButtonHandler());
            b.setPrefWidth(75);
            b.setDisable(true);
            
            btns.add(b);
            gp.add(b, i+2,  0);
        }
        gp.add(new ExitButton(), 6, 0);//Row 1, Col 7
        btns.get(0).setDisable(false);
        
        StackPane valAlign = new StackPane();//just for aligning validation msg
            valAlign.setAlignment(Pos.CENTER_RIGHT);
            valAlign.getChildren().add(valMsg);
            valMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px");
         
        //Row 2, Col 1 & 2
        gp.add(valAlign, 0, 1, 2, 1);
        return gp;
    }
    
    /**
     * Defines actions for Insert, Delete, Balance, and Clear buttons
     * Initiates construction of tree animations
     */
    class ButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            Validator val = new Validator();
            valMsg.setText(null); // Reset validation message
            
            if(e.getSource() == btns.get(0))//Insert
            {
                //if not empty
                if(!val.isEmpty(tfKey.getText()))
                {
                    //if integer
                    if(val.isValidInt(tfKey.getText()))
                    {
                        int key = Integer.parseInt(tfKey.getText());
                        
                        // no reason to search tree if BST is empty
                        if(bTree.getSize() != 0 && bTree.search(key)) 
                        { // key is in the tree already
                            bView.replaceStatus(key + " is already in the tree");
                            if(avlTree.getSize()!= 0)
                            {
                                avlView.replaceStatus(key + " is already in the tree");
                                rbView.replaceStatus(key + " is already in the tree");
                            }
                        } 
                        else // empty tree
                        {
                            keys.add(key);
                            bTree.insert(key); // Insert a new key
                            bView.displayTree(key + " is inserted in the tree");                
                            avlView.replaceStatus("Click Balance to see changes to the tree");
                            rbView.replaceStatus("Click Balance to see changes to the tree");
                            needsBalance = true;
                        }
                        buttonAbility(false); // Enable Delete, Balance, Clear
                    }
                    else//input is not integer
                    {
                        valMsg.setText("Invalid entry");
                    }
                }
                else//empty field
                {
                    valMsg.setText("*Required field");
                }
            }
            
            else if(e.getSource() == btns.get(1))//Delete
            {   
                //not empty
                if(!val.isEmpty(tfKey.getText()))
                {
                    //valid integer
                    if(val.isValidInt(tfKey.getText()))
                    {
                        int key = Integer.parseInt(tfKey.getText());
                        
                        if (!bTree.search(key))
                        { // key is not in the tree, nothing to delete
                            bView.replaceStatus(key + " is not in the tree");
                            if(avlTree.getSize() != 0)//AVL & RB Trees were created with Balance button
                            {
                                //show the tree with new message
                                avlView.replaceStatus(key + "is not in the tree");
                                rbView.replaceStatus(key + "is not in the tree");
                            }
                        } 
                        
                        else // key exists
                        {
                            if(bTree.getSize() == 1) // Deletion will empty tree
                            {
                                bTree.clear();
                                bView.displayTree(key + " deleted from tree. Tree is empty");
                                if(avlTree.getSize() != 0) // Balance was clicked, creating AVL & RB
                                {
                                    avlTree.clear();
                                    avlView.displayTree(key + " deleted from tree. Tree is empty");
                                    rbTree.clear();
                                    rbView.displayTree(key + " deleted from tree. Tree is empty");
                                }
                                else
                                {
                                    avlView.replaceStatus("Tree is empty");
                                    rbView.replaceStatus("Tree is empty");
                                }
                                buttonAbility(true); // disable buttons
                            }
                            
                            else // Deletion won't empty tree
                            {
                                bTree.delete(key);
                                bView.displayTree(key + " deleted from tree");
                                
                                if(avlTree.getSize() != 0)
                                {
                                    avlTree.delete(key);
                                    avlView.displayTree(key + " deleted from tree");
                                    
                                    rbTree.delete(key);
                                    rbView.displayTree(key + " deleted from tree");
                                }
                            }
                            //remove key from keys array
                            keys.remove(indexOf(key));
                            needsBalance = false;
                        }
                    }
                    
                    else//invalid integer
                    {
                        valMsg.setText("Invalid entry");
                    }
                }
                else//empty field
                {
                    valMsg.setText("*Required field");
                }
            }
            
            else if(e.getSource() == btns.get(2))//Balance
            {
                //if a BST was created, its nodes were put in keys ArrayList
                if(bTree.getSize() != 0)
                {
                    if(needsBalance == true) // Tree wasn't just balanced
                    {
                        for(int i = 0; i < keys.size(); i++)
                        {
                            avlTree.insert(keys.get(i));
                            rbTree.insert(keys.get(i));
                        }
                        avlView.displayTree("The AVL Tree");
                        rbView.displayTree("The Red-Black Tree");
                        needsBalance = false;
                    }
                    else // Nodes exist, but are already balanced; don't rebuild the tree
                    {
                        avlView.replaceStatus("AVL Tree is already balanced!");
                        rbView.replaceStatus("Red-Black Tree is already balanced!");
                    }
                }
                else//no BST to balance
                {
                    avlView.displayTree("No tree to balance");
                    rbView.displayTree("No tree to balance");
                }
            }
            else//Clear
            {
                AlertSetter confirm = 
                        new AlertSetter(Alert.AlertType.CONFIRMATION, 
                                "Confirm Clear",
                                "Are you sure you want to continue?",
                                "Hitting OK will erase all trees and their underlying structures.");
                
                Optional<ButtonType> clearOK = confirm.getChoice();
                
                //User confirms clear
                if((clearOK.isPresent()) && (clearOK.get() == ButtonType.OK))
                {                     
                    //clear trees, keys array
                    keys.clear();
                    bTree.clear();
                    avlTree.clear();
                    rbTree.clear();
                
                    //display trees with Deleted status
                    bView.displayTree("BST Tree deleted.");
                    avlView.displayTree("AVL Tree deleted.");
                    rbView.displayTree("Red-Black Tree deleted.");
                
                    needsBalance = false;
                    buttonAbility(true);      
                }
            }    
        tfKey.setText("");
        tfKey.requestFocus();
        }     
    }
    
    /**
     * Enables or disables Delete, Clear, Balance buttons according to boolean arg
     * @param b 
     */
    private void buttonAbility(boolean b)
    {
        for(int i = 1; i <= 3; i++)
        {
            btns.get(i).setDisable(b);
        }  
    }
    
    /**
     * Returns the index of a key in ArrayList using binary search for speed
     * Adapted from: http://algs4.cs.princeton.edu/11model/BinarySearch.java.html
     * @param key
     * @return 
     */
    private int indexOf(int key)
    {
        int lo = 0;
        int hi = keys.size() - 1;
        while(lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if(key < keys.get(mid))//search target less than element at index Mid
            {
                hi = mid - 1;//search the first half of this sublist
                
            }
            else if(key > keys.get(mid))//search target greater than element at index Mid
            {
                lo = mid + 1;//search the second half of sublist
            }
            else//target element located at index Mid
            {
                return mid;
            }
        }  
        return -1;
    }
} //End Class CHirschberger_Week2