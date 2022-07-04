public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                board[endLine][endColumn].check = false;
                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean isJumpOverFigure(int startLine, int startColumn, int endLine, int endColumn){
        boolean status;
        //System.out.printf(this.nowPlayerColor() + "->" + board[endLine][endColumn].getColor() + " ");
        switch (board[startLine][startColumn].getSymbol()){
            case "B": //Слон
            {
                System.out.printf("B ");
                if (startLine - endLine == startColumn - endColumn){
                    // Левый низ - правый верх
                    return isJumpOverFigureLbottomRtop(startLine,startColumn,endLine,endColumn);
                }
                if (startLine + startColumn == endLine + endColumn){
                    // левый верх - правый низ
                    return isJumpOverFigureLtopRbuttom(startLine,startColumn,endLine,endColumn);
                }
            }
            break;
            case "R": //Ладья
            {
                System.out.printf("R ");
                if (startLine == endLine && startColumn != endColumn){
                    // По горизoнтали
                    return isJumpOverFigureHorisontal(startLine,startColumn,endLine,endColumn);
                }
                if (startLine != endLine && startColumn == endColumn){
                    // По вертикали
                    return isJumpOverFigureVertical(startLine,startColumn,endLine,endColumn);
                }
            }
            break;
            case "Q", "K": //Ферзь, Король
            {
                System.out.printf("QK ");
                if (startLine - endLine == startColumn - endColumn){
                    // Левый низ - правый верх
                    return isJumpOverFigureLbottomRtop(startLine,startColumn,endLine,endColumn);
                }
                if (startLine + startColumn == endLine + endColumn){
                    // левый верх - правый низ
                    return isJumpOverFigureLtopRbuttom(startLine,startColumn,endLine,endColumn);
                }
                if (startLine == endLine && startColumn != endColumn){
                    // По горизнтали
                    return isJumpOverFigureHorisontal(startLine,startColumn,endLine,endColumn);
                }
                if (startLine != endLine && startColumn == endColumn){
                    // По вертикали
                    return isJumpOverFigureVertical(startLine,startColumn,endLine,endColumn);
                }
            }
            break;
            default: return true;
        }
        return false;
    }


    public boolean canEatOrStay(int endLine, int endColumn){
        if (this.board[endLine][endColumn] == null){  //нет фигуры - можно поставить
            return true;
        }
        else //если есть, и если это фигура противника, то едим.
        {
            if (this.nowPlayerColor() != this.board[endLine][endColumn].getColor()){   //ест
                return true;
            } else return false;
        }

    }
    private boolean isJumpOverFigureHorisontal(int startLine, int startColumn, int endLine, int endColumn){
        if (startColumn < endColumn){
            for (int i = startColumn + 1; i < endColumn; i++){
                if (board[startLine][i] != null){
                    return true;
                }
            }
        }
        else {
            for (int i = startColumn - 1; i > endColumn; i--){
                if (board[startLine][i] != null){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isJumpOverFigureVertical(int startLine, int startColumn, int endLine, int endColumn){
        if (startLine < endLine){
            for (int i = startLine + 1; i < endLine; i++){
                if (board[i][startColumn] != null){
                    return true;
                }
            }
        }
        else {
            for (int i = startLine - 1; i > endLine; i--){
                if (board[i][startColumn] != null){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isJumpOverFigureLbottomRtop(int startLine, int startColumn, int endLine, int endColumn){
        if (startLine < endLine){
            for (int i = startLine + 1; i < endLine; i++){
                if (board[i][startColumn + i] != null){
                    return true;
                }
            }
        }
        else {
            for (int i = startLine - 1; i > endLine; i--){
                if (board[i][startLine - i] != null){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isJumpOverFigureLtopRbuttom(int startLine, int startColumn, int endLine, int endColumn){
        if (startLine < endLine){
            for (int i = startLine + 1; i < endLine; i++){
                if (board[i][startColumn - (i - startLine)] != null){
                    return true;
                }
            }
        }
        else {
            for (int i = startLine - 1; i > endLine; i--){
                if (board[i][startColumn + (startLine - i)] != null){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White");   // move King
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][5] == null && board[0][6] == null) {              // never moved
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][6] = new King("White");   // move King
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // move Rook
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][5] == null && board[7][6] == null) {              // never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // move King
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // move Rook
                    board[7][5].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

}