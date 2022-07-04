public class King extends ChessPiece {

    public King(String color) {
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

                if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1 ){
                    return false;
                }
                else return chessBoard.canEatOrStay(toLine, toColumn);
            }

            else return  false;
        }
        else return false;
    }
    public boolean isUnderAttack(ChessBoard board, int line, int column){
            return false;
    }


    @Override
    String getSymbol() {
        return "K";
    }



}
