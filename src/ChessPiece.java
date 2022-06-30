public abstract class ChessPiece {
    public String color;
    public  boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }


    public abstract String getColor();
    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
    abstract String getSymbol();

    public boolean isInTheBoardAndMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0 || (toLine == line && toColumn == column)) {
            return false;
        }
        return true;
    }

}
