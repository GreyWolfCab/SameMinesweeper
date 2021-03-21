public class MainApp {

    public static void main(String agrs[]) {

        Board board = new GenerateBoard(9, 9);

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                System.out.print(board.accessBoard(i, j) + ", ");

            }

            System.out.println();
        }

    }

}
