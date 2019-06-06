class Logic {
    private int[][] board = new int[4][4];
    private int idles = 16;
    private boolean isChanged = false;
    private int score = 0;

    void newGame() {
        board = new int[4][4];
        idles = 16;
        isChanged = false;
        score = 0;
        addNumber();
        addNumber();
    }

    private void addNumber() {
        if(idles > 0) {
            int position = (int) (Math.random() * 16);
            while(position == 16 || board[position / 4][position % 4] != 0) {
                position = (int) (Math.random() * 16);
            }
            int value = (int) (Math.random() * 10) > 5 ? 4 : 2;
            board[position / 4][position % 4] = value;
            idles--;
        }
    }

    void move(int direction) {
        switch(direction) {
            case 1 : isChanged = moveUp(); break;
            case 2 : isChanged = moveDown(); break;
            case 3 : isChanged = moveLeft(); break;
            case 4 : isChanged = moveRight(); break;
            default : System.out.println("Invaild Command!");
        }

        if(isChanged) {
            addNumber();
        }
    }

    private boolean moveUp() {
        boolean result = false;
        for(int i = 0; i < 4; i++) {
            int limit = -1;
            for(int j = 1; j < 4; j++) {
                if(board[j][i] != 0) {
                    int m = j - 1;
                    while(m >= 0) {
                        if(board[m][i] == 0) {
                            if(m == 0) {
                                board[m][i] = board[j][i];
                                board[j][i] = 0;
                                limit = m;
                                result = true;
                                break;
                            } else {
                                m--;
                            }
                        } else if(board[m][i] == board[j][i] && m > limit){
                            board[j][i] = 0;
                            board[m][i] *= 2;
                            limit = m;
                            idles++;
                            result = true;
                            score += board[m][i];
                            break;
                        } else if((m + 1) != j){
                            int temp = board[j][i];
                            board[j][i] = 0;
                            board[m + 1][i] = temp;
                            result = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean moveDown() {
        boolean result = false;
        for(int i = 0; i < 4; i++) {
            int limit = 4;
            for(int j = 2; j >= 0; j--) {
                if(board[j][i] != 0) {
                    int m = j + 1;
                    while(m < 4) {
                        if(board[m][i] == 0) {
                            if(m == 3) {
                                board[m][i] = board[j][i];
                                board[j][i] = 0;
                                limit = m;
                                result = true;
                                break;
                            } else {
                                m++;
                            }
                        } else if(board[m][i] == board[j][i] && m < limit) {
                            board[j][i] = 0;
                            board[m][i] *= 2;
                            limit = m;
                            idles++;
                            result = true;
                            score += board[m][i];
                            break;
                        } else if((m - 1) != j) {
                            int temp = board[j][i];
                            board[j][i] = 0;
                            board[m - 1][i] = temp;
                            result = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean moveLeft() {
        boolean result = false;
        for(int i = 0; i < 4; i++) {
            int limit = -1;
            for(int j = 1; j < 4; j++) {
                if(board[i][j] != 0) {
                    int m = j - 1;
                    while(m >= 0) {
                        if(board[i][m] == 0) {
                            if(m == 0) {
                                board[i][m] = board[i][j];
                                board[i][j] = 0;
                                limit = m;
                                result = true;
                                break;
                            } else {
                                m--;
                            }
                        } else if(board[i][m] == board[i][j] && m > limit){
                            board[i][j] = 0;
                            board[i][m] *= 2;
                            limit = m;
                            idles++;
                            result = true;
                            score += board[m][i];
                            break;
                        } else if((m + 1) != j) {
                            int temp = board[i][j];
                            board[i][j] = 0;
                            board[i][m + 1] = temp;
                            result = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean moveRight() {
        boolean result = false;
        for(int i = 0; i < 4; i++) {
            int limit = 4;
            for(int j = 2; j >= 0; j--) {
                if(board[i][j] != 0) {
                    int m = j + 1;
                    while(m < 4) {
                        if(board[i][m] == 0) {
                            if(m == 3) {
                                board[i][m] = board[i][j];
                                board[i][j] = 0;
                                limit = m;
                                result = true;
                                break;
                            } else {
                                m++;
                            }
                        } else if(board[i][m] == board[i][j] && m < limit){
                            board[i][j] = 0;
                            board[i][m] *= 2;
                            limit = m;
                            idles++;
                            result = true;
                            score += board[m][i];
                            break;
                        } else if((m - 1) != j) {
                            int temp = board[i][j];
                            board[i][j] = 0;
                            board[i][m - 1] = temp;
                            result = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    int[][] getBoard() {
        return board;
    }

    boolean isOver() {
        return idles <= 0 && !isChanged;

    }

    int getScore() {
        return score;
    }

}
