package com.webcheckers.board;

import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;

import java.util.*;

/**
 * A class to represent the game board.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class BoardView implements Iterable<Row>{
    private Player red;
    private Player white;
    private Space[][] board;
    private MoveType lastMoveType = MoveType.NONE;

    private enum MoveType {
        NONE,
        SIMPLE,
        JUMP
    }

    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;

    static final Message NULL_SPACE_MESSAGE = Message.error("You cannot move to an invalid space!");
    static final Message NULL_START_PIECE_MESSAGE = Message.error("You must move a checker!");
    static final Message OPPONENTS_PIECE_MESSAGE = Message.error("You cannot move your opponents pieces!");
    static final Message OCCUPIED_END_SPACE_MESSAGE = Message.error("You cannot to an occupied square!");
    static final Message FORCED_JUMP_MESSAGE = Message.error("When a jump is possible, you must must jump!");
    static final Message VALID_MOVE_MESSAGE = Message.info("That move is legal.");
    static final Message ILLEGAL_MOVE_MESSAGE = Message.error("That piece cannot move like that!");
    static final Message VALID_JUMP_MESSAGE = Message.info("That jump is legal.");
    static final Message JUMP_OVER_NOTHING_MESSAGE = Message.error("You cannot jump over an empty square!");
    static final Message JUMP_OVER_OWN_PIECE_MESSAGE = Message.error("You cannot jump over your own piece!");
    static final Message DOUBLE_MOVE_MESSAGE = Message.error("You cannot move twice in one turn!");
    static final Message MOVE_AFTER_JUMPING_MESSAGE = Message.error("You cannot move after jumping!");
    static final Message JUMP_AFTER_MOVING_MESSAGE = Message.error("You cannot jump after moving!");

    static final Message INVALID_MOVE_MESSAGE = Message.error("That piece cannot move there!");


    /**
     * Creates a new game board.
     *
     * @param red the player using red pieces
     * @param white the player using white pieces
     */
    public BoardView(Player red, Player white){
        this.red = red;
        this.white = white;
        this.board = new Space[NUM_ROWS][NUM_COLS];
        initBoard();
    }

    /**
     * A copy constructor that can also return a flipped copy of the board.
     *
     * @param board the board to be copied
     * @param flip whether or not the board should be flipped
     */
    public BoardView(BoardView board, boolean flip) {
        this.board = new Space[NUM_ROWS][NUM_COLS];
        for(int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if(flip) {
                    this.board[row][col] = new Space(board.getRow(NUM_ROWS - row - 1)[NUM_COLS - col - 1]);
                } else {
                    this.board[row][col] = new Space(board.getRow(row)[col]);
                }
            }
        }
    }

    /**
     * Checks if the move passed in is a valid move to make.
     *
     * @param move the move to check
     * @return a message about the validity of the move being checked
     */
    public Message checkMove(Move move, Piece.Color playerColor) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Space startSpace = getSpace(start);
        Space endSpace = getSpace(end);

        if(startSpace == null || endSpace == null) {
            return NULL_SPACE_MESSAGE;
        }

        Piece startPiece = startSpace.getPiece();

        if(startPiece == null) {
            return NULL_START_PIECE_MESSAGE;
        }

        if(startPiece.getColor() != playerColor) {
            return OPPONENTS_PIECE_MESSAGE;
        }

        if(endSpace.getPiece() != null) {
            return OCCUPIED_END_SPACE_MESSAGE;
        }

        if(move.isSimpleMove()) {
            if(lastMoveType == MoveType.SIMPLE) {
                return DOUBLE_MOVE_MESSAGE;
            } else if(lastMoveType == MoveType.JUMP) {
                return MOVE_AFTER_JUMPING_MESSAGE;
            }

            if(canJump(playerColor)) {
                return FORCED_JUMP_MESSAGE;
            }

            if(startPiece.isMoveValid(move)) {
                return VALID_MOVE_MESSAGE;
            } else {
                return ILLEGAL_MOVE_MESSAGE;
            }
        } else if(move.isJump()) {
            if(lastMoveType == MoveType.SIMPLE) {
                return JUMP_AFTER_MOVING_MESSAGE;
            }

            if(startPiece.isJumpValid(move)) {
                return VALID_JUMP_MESSAGE;
            } else {
                Space jumpedSquare = getJumpedSquare(move);
                Piece jumpedPiece = jumpedSquare.getPiece();
                if(jumpedPiece == null) {
                    return JUMP_OVER_NOTHING_MESSAGE;
                } else if(jumpedPiece.getColor() == playerColor) {
                    return JUMP_OVER_OWN_PIECE_MESSAGE;
                }
            }
        }
        return INVALID_MOVE_MESSAGE;
    }

    public void makeMove(Move move) {
        Position start = move.getStart();
        Position end = move.getEnd();

        Space startSpace = getSpace(start);
        Space endSpace = getSpace(end);
        assert startSpace != null;
        assert endSpace != null;
        Piece piece = startSpace.getPiece();

        startSpace.setPiece(null);
        if(piece.getType() == Piece.Type.SINGLE && end.getRow() == 0) {
            endSpace.setPiece(new King(piece.getColor()));
        } else {
            endSpace.setPiece(piece);
        }

        if(move.isJump()) {
            getJumpedSquare(move).setPiece(null);
            lastMoveType = MoveType.JUMP;
        } else {
            lastMoveType = MoveType.SIMPLE;
        }
    }

    public Space getJumpedSquare(Move move) {
        Position start = move.getStart();
        Position end = move.getEnd();
        int row = (start.getRow() + end.getRow()) / 2;
        int cell = (start.getCell() + end.getCell()) / 2;
        return board[row][cell];
    }

    private boolean canJump(Piece.Color playerColor) {
        return false;
    }

    private Space getSpace(Position position) {
        if(validPosition(position)) {
            return board[position.getRow()][position.getCell()];
        }
        return null;
    }

    public boolean validPosition(Position position) {
        boolean validRow = ((position.getRow() < NUM_ROWS) && (position.getRow() >= 0));
        boolean validCol = ((position.getCell() < NUM_COLS) && (position.getCell() >= 0));
        return validRow && validCol;
    }

    /**
     * Returns the row at the index given.
     *
     * @param rowIdx the index of the desired row
     * @return the row at the given index
     */
    private Space[] getRow(int rowIdx) {
        return board[rowIdx];
    }

    /**
     * Initialize the board to a valid initial board state.
     */
    public void initBoard() {
        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col < NUM_COLS; col++) {
                if((row + col) % 2 == 1) {
                    if(row <= 2) {
                        board[row][col] = new Space(col, new Single(Piece.Color.WHITE), true);
                    } else if(row >= 5) {
                        board[row][col] = new Space(col, new Single(Piece.Color.RED), true);
                    } else {
                        board[row][col] = new Space(col, null, true);
                    }
                } else {
                    board[row][col] = new Space(col, null, false);
                }
            }
        }
    }

    /**
     * Return an iterator of the board.
     *
     * @return a linked list of the rows in the board
     */
    @Override
    public Iterator<Row> iterator(){
        Collection<Row> lst = new LinkedList<>();
        for(int row = 0; row < NUM_ROWS; row++) {
            lst.add(new Row(row, Arrays.asList(board[row])));
        }
        return lst.iterator();
    }

}