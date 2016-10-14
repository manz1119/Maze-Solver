/*  Michael Manzanares
    masc0363
*/
//import data_structures.*;

public class MazeSolver {
        private MazeGrid magicMaze;
        private Stack<GridCell> stack;
        private Queue<GridCell> queue;
        private GridCell start;
        private GridCell end;
        private int DIMENSION;
       
    public MazeSolver(int dimension) {
        magicMaze = new MazeGrid(this, dimension);
        stack = new Stack<GridCell>();
        queue = new Queue<GridCell>();
        start = magicMaze.getCell(0,0);
        end = magicMaze.getCell(dimension - 1, dimension - 1);
        DIMENSION  =  dimension;
        }
    public void reset() {
        stack.makeEmpty();    
        }
    public void mark() {
        start.setDistance(0);
        queue.enqueue(start);
        
        while(!queue.isEmpty()){
            GridCell temp = queue.dequeue();
            GridCell up = magicMaze.getCell(temp.getX()-1, temp.getY());
            GridCell down = magicMaze.getCell(temp.getX()+1, temp.getY());
            GridCell right = magicMaze.getCell(temp.getX(), temp.getY()+1);
            GridCell left = magicMaze.getCell(temp.getX(), temp.getY()-1);
            
            if(magicMaze.isValidMove(right) && !right.wasVisited()) {
                right.setDistance(temp.getDistance()+1);
                magicMaze.markDistance(right);
                queue.enqueue(right);
                }
            if(magicMaze.isValidMove(down) && !down.wasVisited()) {
                down.setDistance(temp.getDistance()+1);
                magicMaze.markDistance(down);
                queue.enqueue(down);
                }
            if(magicMaze.isValidMove(left) && !left.wasVisited()) {
                left.setDistance(temp.getDistance()+1);
                magicMaze.markDistance(left);
                queue.enqueue(left);
                }
            if(magicMaze.isValidMove(up) && !up.wasVisited()) {
                up.setDistance(temp.getDistance()+1);
                magicMaze.markDistance(up);
                queue.enqueue(up);
            }
        }
    }
    public boolean move() {
        int distance = end.getDistance();
        stack.push(magicMaze.getCell(DIMENSION-1, DIMENSION-1));
        if(distance == -1) return false;
        stack.push(end);

        while(distance != 0) {
            int x = stack.peek().getX();
            int y = stack.peek().getY();
            GridCell up = magicMaze.getCell(x-1, y);
            GridCell left = magicMaze.getCell(x, y-1);
            GridCell down = magicMaze.getCell(x+1, y);
            GridCell right = magicMaze.getCell(x, y+1);

            if(magicMaze.isValidMove(up)&&up.getDistance() < distance)
                stack.push(up);
            else if(magicMaze.isValidMove(left)&&left.getDistance() < distance)
                stack.push(left);
            else if(magicMaze.isValidMove(down)&&down.getDistance() < distance)
                stack.push(down);
            else if(magicMaze.isValidMove(right)&&right.getDistance() < distance)
                stack.push(right);
            distance --;
            }
        while(!stack.isEmpty()) {
            magicMaze.markMove(stack.pop());
            }
        return true;
    }
    public static void main(String[] arg) {
        new MazeSolver(40 );
    }
}