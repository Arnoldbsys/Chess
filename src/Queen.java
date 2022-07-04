public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }


    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (isInTheBoardAndMove(chessBoard, line, column, toLine, toColumn)) {

            if (line - toLine == column - toColumn || (line + column == toLine + toColumn)
            || line - toLine == 0 || column - toColumn == 0){
                if (!chessBoard.isJumpOverFigure(line, column, toLine, toColumn)){  //не перепрыгивает через другие
                    return chessBoard.canEatOrStay(toLine, toColumn);
                }
                else  return false;
            }

            else return  false;
        }
        else return false;
    }

    @Override
    String getSymbol() {
        return "Q";
    }



}
