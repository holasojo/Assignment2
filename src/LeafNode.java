
public class LeafNode implements QuadNode {

    PtList list;

    public LeafNode() {
        list = new PtList();
    }

    public boolean isLeaf() {
        return true;
    }

    public int dump(int x, int y, int width, int level) {
        String n = QuadNode.spaces(level);

        System.out.println(n + "Node at " + x + ", " + y + ", " + width + ":");

        String[] elems = list.list();

        for (String elem : elems) {
            System.out.println(n + "(" + elem + ")");
        }

        return 1;

    }

    @Override
    public QuadNode insert(Point pt, int x, int y, int width) {
        if (list.size() >= 3 && !list.checkAllSame(pt)) {
            Point[] points = list.remove();
            IntlNode internal = new IntlNode();

            for (Point it : points) {
                internal.insert(it, x, y, width);
            }
            internal.insert(pt, x, y, width);
            return internal;
        }
        list.append(pt);
        return this;
    }

    @Override
    public QuadNode remove(Point pt, int x, int y, int width) {

        list.remove(pt);

        if (list.size() == 0) {
            return IntlNode.flyweight();
        }
        return this;
    }

    public int pointCount() {
        return list.size();
    }

    public Point[] removeAll() {
        return list.remove();
    }

    public Point searchbyCoor(Point pt, int x, int y, int width) {

        return list.findbyCoor(pt);

    }

    public void regionSearch(int x, int y, int w, int h, int xWorld, int yWrold,
            int widthWorld, int nodeCount) {
        Point[] points = list.remove();

        for (Point it : points) {
            if (it.inRegion(x, y, w, h)) {
                System.out.println("Point found: " + it.toString());
            }
        }
        System.out.println(nodeCount + " quadtree nodes visited");

    }

    @Override
    public void duplicates(int x, int y, int width) {

        Point[] elems = list.remove();

        for (Point elem1 : elems) {
            for (Point elem2 : elems) {
                if (elem1 != elem2 && elem1.equalsCoor(elem2)) {
                    System.out.println(elem1.toString());
                    break;
                }
            }
        }
    }

}
