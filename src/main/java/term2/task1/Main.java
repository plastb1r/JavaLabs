package term2.task1;

// Сделать однопоточное многопоточное перемножение матриц
// Сортировка шелла(доп)
// Смоделировать работу банка с пом потоков

// Объекты:

// Касса наличка

// В случайные моменты времени поток генерирует клиентов
// (поля : зачем пришёл положить снять, сумма с которой он пришёл, время его обслуживания)

// Операционистки( работают с единой кассой) 
// Генератор генерит клиента, идёт в очередь к операционистке с меньшей очередью
// Попал к операционистке, если клиент хочет взять деньги, то такая сумма должна быть в кассе,
// операционистке засыпает на время обслуживания клиента, если денег в кассе нет,
// то создать очередь которая ждёт пока в кассе появятся деньги.

import java.util.Random;

class Main {

  final static int FIRST_MATRIX_ROWS = 2000;
  final static int FIRST_MATRIX_COLS = 1000;
  final static int SECOND_MATRIX_ROWS = 1000;
  final static int SECOND_MATRIX_COLS = 1000;

  public static void main(String[] args) {
    final float[][] firstMatrix = new float[FIRST_MATRIX_ROWS][FIRST_MATRIX_COLS];
    final float[][] secondMatrix = new float[SECOND_MATRIX_ROWS][SECOND_MATRIX_COLS];

    fillMatrix(firstMatrix);
    fillMatrix(secondMatrix);

    long startTime = System.currentTimeMillis();
    final float[][] resultMatrix = simpleMatrixMultiplication(firstMatrix, secondMatrix);
    long simpleExecTime = System.currentTimeMillis() - startTime;

    startTime = System.currentTimeMillis();
    final float[][] parallelResultMatrix = parallelMatrixMultiplication(firstMatrix, secondMatrix,
        Runtime.getRuntime().availableProcessors());
    long parallelExecTime = System.currentTimeMillis() - startTime;

    for (int row = 0; row < FIRST_MATRIX_ROWS; row++) {
      for (int col = 0; col < SECOND_MATRIX_COLS; col++) {
        if (parallelResultMatrix[row][col] != resultMatrix[row][col]) {
          System.out.println("Error in parallel calculation!");
          return;
        }
      }
    }

    System.out.println("Calculation succeed!\n");
    System.out.format("Time spent on parallel execution - %d ms\n", parallelExecTime);
    System.out.format("Time spent on sequential execution - %d ms", simpleExecTime);

    // System.out.println();
    // printMatrix(firstMatrix);
    // System.out.println();
    // printMatrix(secondMatrix);
    // System.out.println();
    // printMatrix(resultMatrix);
    // System.out.println();
  }

  public static void fillMatrix(final float[][] matrix) {
    final Random random = new Random();

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        matrix[row][col] = random.nextFloat() * 100;
        // matrix[row][col] = random.nextInt(5);
      }
    }
  }

  public static float[][] simpleMatrixMultiplication(final float[][] firstMatrix, final float[][] secondMatrix) {
    final float[][] result = new float[firstMatrix.length][secondMatrix[0].length];

    for (int row = 0; row < firstMatrix.length; row++) {
      for (int col = 0; col < secondMatrix[0].length; col++) {
        float sum = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
          sum += firstMatrix[row][i] * secondMatrix[i][col];
        }
        result[row][col] = sum;
      }
    }

    return result;
  }

  public static float[][] parallelMatrixMultiplication(final float[][] firstMatrix, final float[][] secondMatrix,
      int threadCount) {
    final float[][] result = new float[firstMatrix.length][secondMatrix[0].length];

    final int cellsOnThread = (firstMatrix.length * secondMatrix[0].length) / threadCount;
    final MatrixMultiplicationThread[] MatrixMultiplications = new MatrixMultiplicationThread[threadCount];

    int firstIndex = 0;
    for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
      int lastIndex = firstIndex + cellsOnThread;
      if (threadIndex == 0) {
        lastIndex += (firstMatrix.length * secondMatrix[0].length) % threadCount; // если нельзя разделить ячейки
                                                                                  // поровну
      }

      MatrixMultiplications[threadIndex] = new MatrixMultiplicationThread(firstMatrix, secondMatrix, result, firstIndex,
          lastIndex);
      MatrixMultiplications[threadIndex].start();
      firstIndex = lastIndex;
    }

    try {
      for (MatrixMultiplicationThread thread : MatrixMultiplications)
        thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static void printMatrix(float[][] matrix) {
    for (float[] row : matrix) {
      for (float val : row) {
        System.out.format("%.1f ", val);
      }
      System.out.println();
    }
  }

}