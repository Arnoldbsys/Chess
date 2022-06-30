public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isInTheBoardAndMove(chessBoard, line, column, toLine, toColumn) && column == toColumn) {
            if (color.equals("White")){
                if (toLine - line > 0 && toLine - line <= (line == 1 ? 2 : 1) ){
                    return true;
                }
                else return false;
            }
            else {
                if (line - toLine > 0 && line - toLine <= (line == 6 ? 2 : 1)) {
                    return true;
                } else return false;

            }
        }
        else return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }



}
