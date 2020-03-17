package term2.task1;

public class MatrixMultiplicationThread extends Thread {
  private final float[][] firstMatrix;
  private final float[][] secondMatrix;
  private final float[][] resultMatrix;
  private final int from;
  private final int to;

  public MatrixMultiplicationThread(final float[][] firstMatrix, final float[][] secondMatrix,
      final float[][] resultMatrix, final int from, final int to) {
    this.firstMatrix = firstMatrix;
    this.secondMatrix = secondMatrix;
    this.resultMatrix = resultMatrix;
    this.from = from;
    this.to = to;
  }

  @Override
  public void run() {
    System.out.println("[ " + getName() + " ] -- Calculate cells from " + from + " to " + to);

    for (int index = from; index < to; index++) {
      int row = index / secondMatrix[0].length;
      int col = index % secondMatrix[0].length;
      
      float sum = 0;
      for (int i = 0; i < secondMatrix.length; i++) {
        sum += firstMatrix[row][i] * secondMatrix[i][col];
      }
      resultMatrix[row][col] = sum;
    }

    System.out.println("[ " + getName() + " ] -- finished");
  }

}