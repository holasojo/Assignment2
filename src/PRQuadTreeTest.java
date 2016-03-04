
public class PRQuadTreeTest extends student.TestCase {

    Container c;

    public void setUp() {
        c = new Container();

    }

    public void testInsert1() {
        c.tree().dump();
        assertFuzzyEquals("Quadtree dump\n" + "node at 0 0 1024 empty\n" + "1 quadtree nodes printed",
                systemOut().getHistory());
        c.insert("A", 0, 0);
        assertEquals(c.tree().size(), 1);
        systemOut().clearHistory();
        c.tree().dump();
        assertFuzzyEquals("Quadtree dump\n" + "node at 0 0 1024\n" + "a 0 0\n" + "1 quadtree nodes printed",
                systemOut().getHistory());
        c.insert("B", 1, 1);
        c.insert("C", 1000, 1000);
        c.insert("D", 700, 20);
        systemOut().clearHistory();
        c.tree().dump();
        assertFuzzyEquals("Quadtree dump\n" + "node at 0 0 1024 internal\n" + "node at 0 0 512\n" + "a 0 0\n"
                + "b 1 1\n" + "node at 512 0 512\n" + "d 700 20\n" + "node at 0 512 512 empty\n"
                + "node at 512 512 512\n" + "c 1000 1000\n" + "5 quadtree nodes printed", systemOut().getHistory());
        c.insert("E", 2, 800);
        c.insert("F", 20, 500);
        c.insert("G", 300, 300);
        systemOut().clearHistory();
        c.tree().dump();
        assertFuzzyEquals("Quadtree dump\n" + "node at 0 0 1024 internal\n" + "node at 0 0 512 internal\n"
                + "node at 0 0 256\n" + "a 0 0\n" + "b 1 1\n" + "node at 256 0 256 empty\n" + "node at 0 256 256\n"
                + "f 20 500\n" + "node at 256 256 256\n" + "g 300 300\n" + "node at 512 0 512\n" + "d 700 20\n"
                + "node at 0 512 512\n" + "e 2 800\n" + "node at 512 512 512\n" + "c 1000 1000\n"
                + "9 quadtree nodes printed", systemOut().getHistory());
    }

    /**
     * testing duplicates and splitting behavior
     * 
     */
    public void testInsert2() {
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.tree().dump();
        c.insert("A", 0, 0);
        c.tree().dump();
        c.insert("A", 0, 0);
        c.tree().dump();
        c.insert("B", 400, 600);
        c.tree().dump();
        c.insert("A", 200, 200);
        c.tree().dump();
    }

    /**
     * testing Syntax Test1
     */
    public void testInsert3() {
        /**
         * insert r_r -1 -20 insert rec 7 -8
         */

        c.insert("r_r", -1, -20);
        c.insert("rec", 7, -8);

        assertFuzzyEquals("Point rejected: (r_r, -1, -20)\n" + "Point rejected: (rec, 7, -8)",
                systemOut().getHistory());
        systemOut().clearHistory();
        c.tree().dump();
        assertFuzzyEquals("Quadtree dump\n" + "node at 0 0 1024 empty\n" + "1 quadtree nodes printed",
                systemOut().getHistory());

    }

    /**
     * testing Syntax Test2
     */
    public void testInsert4() {
        /**
         * 
         * insert r_r 1 20 insert rec 10 30 insert r_42 1 20 insert far 200 200
         */

        c.insert("r_r", 1, 20);
        c.insert("rec", 10, 30);
        c.insert("r_42", 1, 20);
        c.insert("far", 200, 200);
        systemOut().clearHistory();
        c.tree().dump();

        assertFuzzyEquals("QuadTree dump:\n" + "Node at 0, 0, 1024: Internal\n" + "  Node at 0, 0, 512: Internal\n"
                + "    Node at 0, 0, 256: Internal\n" + "      Node at 0, 0, 128:\n" + "      r_r, 1, 20\n"
                + "      rec, 10, 30\n" + "      r_42, 1, 20\n" + "      Node at 128, 0, 128: Empty\n"
                + "      Node at 0, 128, 128: Empty\n" + "      Node at 128, 128, 128:\n" + "      far, 200, 200\n"
                + "    Node at 256, 0, 256: Empty\n" + "    Node at 0, 256, 256: Empty\n"
                + "    Node at 256, 256, 256: Empty\n" + "  Node at 512, 0, 512: Empty\n"
                + "  Node at 0, 512, 512: Empty\n" + "  Node at 512, 512, 512: Empty\n" + "13 quadtree nodes printed\n",
                systemOut().getHistory());

        systemOut().clearHistory();
        c.dump();
    }

    /**
     * P2SyntaxTest1
     */
    public void testRemovebyCoor1() {
        c.insert("r_r", -1, -20);
        c.insert("rec", 7, -8);
        c.removebyCoor(1, -1);
        c.removebyCoor(1, 1);

    }

    /**
     * P2SyntaxTest2
     */
    public void testRemovebyCoor2() {
        c.insert("r_r", 1, 20);
        c.insert("rec", 10, 30);
        c.insert("r_42", 1, 20);
        c.insert("far", 200, 200);
        systemOut().clearHistory();
        c.dump();
        c.removebyName("r_r");
        c.dump();
        c.removebyCoor(10, 30);
        c.dump();
        
    }
    
    public void testRemovebyCoor3(){
        c.insert("A", 0, 0);
        c.insert("B", 1, 1);
        c.insert("C", 1000, 1000);
        c.insert("D", 700, 20);  
        c.insert("E", 2, 800);
        c.insert("F", 20, 500);
        c.insert("G", 300, 300);   
        c.tree().dump();

    }
    
    public void testRemovebyCoor4(){
        
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.insert("A", 0, 0);
        c.insert("A", 1, 1);
        c.tree().dump();
        c.removebyCoor(1, 1);
        c.removebyCoor(0, 0);
        c.removebyCoor(0, 0);
        c.removebyCoor(0, 0);
        c.removebyCoor(0, 0);
        c.removebyCoor(0, 0);
        c.removebyCoor(0, 0);
        c.removebyCoor(0, 0);
        c.tree().dump();
        
    }
    
    public void testSyntax2(){
        
    }

}
