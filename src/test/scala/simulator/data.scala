package simulator

import breeze.linalg._
import scala.io.Source

object Data {
  // input pics 32*32
  val pics = DenseMatrix(
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 91, 154, 254, 254, 255, 169, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 17, 200, 253, 253, 253, 253, 253, 253, 189, 76, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 66, 253, 253, 249, 146, 146, 195, 253, 253, 253, 167, 7, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 196, 253, 189, 55, 0, 0, 26, 83, 237, 253, 253, 37, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 195, 253, 177, 88, 0, 0, 0, 0, 0, 131, 253, 253, 179, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 253, 197, 16, 0, 0, 0, 0, 0, 0, 131, 253, 253, 179, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 190, 187, 0, 0, 0, 0, 0, 0, 23, 198, 253, 253, 179, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 18, 0, 0, 0, 0, 0, 0, 42, 253, 253, 253, 30, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 42, 253, 253, 253, 15, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 201, 253, 253, 205, 10, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 9, 9, 7, 6, 1, 0, 0, 0, 79, 238, 253, 253, 106, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 13, 174, 253, 253, 213, 194, 103, 99, 99, 99, 169, 253, 253, 230, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 2, 95, 197, 253, 253, 253, 253, 253, 253, 253, 253, 253, 253, 253, 240, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 17, 253, 253, 253, 156, 138, 138, 138, 250, 253, 253, 253, 253, 253, 122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 149, 253, 248, 132, 8, 0, 11, 42, 141, 253, 253, 253, 253, 253, 122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 180, 253, 220, 0, 0, 73, 163, 253, 253, 253, 253, 253, 253, 253, 190, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 180, 253, 249, 222, 222, 239, 253, 253, 253, 253, 230, 195, 221, 253, 253, 195, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 85, 253, 253, 253, 253, 253, 253, 253, 205, 106, 65, 0, 116, 253, 253, 229, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 12, 179, 237, 253, 253, 253, 186, 78, 10, 0, 0, 0, 116, 253, 253, 253, 106, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 70, 89, 89, 89, 9, 0, 0, 0, 0, 0, 41, 116, 253, 215, 37, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  )

  // flts1  5*5*6
  val flts1 = DenseVector(
    DenseMatrix((3, 5, -1, -5, -1),
      (4, -2, 3, -4, 0),
      (2, -1, 3, 3, 3),
      (-4, 3, -4, 5, 3),
      (-4, 3, -3, 4, -1)),
    DenseMatrix((-1, 5, -2, 1, -5),
      (-3, 4, -5, -5, 1),
      (-3, 0, 5, -1, -2),
      (-2, 0, -4, -3, 4),
      (-1, 0, 1, 1, -1)),
    DenseMatrix((-4, -4, -5, -1, -4),
      (-3, 4, 2, -1, 2),
      (0, 1, 5, 1, 0),
      (3, -5, -4, 5, 5),
      (0, 4, -5, 1, -2)),
    DenseMatrix((-5, -3, 5, -2, 3),
      (5, -1, -5, 4, 5),
      (-5, -1, -1, -5, 5),
      (4, -3, 5, -3, 0),
      (-2, -4, 3, 3, 4)),
    DenseMatrix((2, 3, -4, 5, 2),
      (-1, -4, 1, -5, -1),
      (-1, -1, -5, 1, -5),
      (-3, -5, 5, 1, -3),
      (3, 0, -3, 4, 0)),
    DenseMatrix((0, -2, 3, 2, -1),
      (1, 4, 5, -2, 5),
      (2, 4, 4, -4, 3),
      (-3, 5, -5, 4, -3),
      (-3, 3, -2, -2, 4))
  )
  // filter2 *16
  val flts2 = DenseVector(
    DenseMatrix((0, -4, 3, -3, -3),
      (4, 0, 0, -3, -3),
      (3, 5, -4, -1, 2),
      (3, -5, 5, -3, 5),
      (-2, 1, -2, -3, 3)),
    DenseMatrix((-3, 3, -4, 0, 3),
      (-2, 5, -3, -4, 1),
      (-2, -5, 1, 1, 1),
      (4, 2, -1, -4, -3),
      (1, -5, 1, 1, 5)),
    DenseMatrix((5, -1, -3, 0, -1),
      (5, 4, 4, -5, -5),
      (0, 3, -2, 5, 3),
      (-4, -1, 4, 5, -2),
      (-5, 4, -5, -3, -3)),
    DenseMatrix((2, -3, -2, -4, 4),
      (1, -2, 0, -4, 0),
      (1, -3, 5, 3, -3),
      (3, 0, 3, 5, -5),
      (3, 5, 1, -4, -1)),
    DenseMatrix((1, 2, 4, -3, -2),
      (-1, 4, 4, 3, 2),
      (-1, 1, -5, -1, 5),
      (-4, -4, -3, -2, -4),
      (3, -3, 0, -4, 3)),
    DenseMatrix((3, 2, -2, 2, -2),
      (4, 4, 1, -5, -2),
      (0, -5, -2, -5, 1),
      (4, 1, 0, 4, 5),
      (4, 0, 4, -5, 0)),
    DenseMatrix((-3, 4, 4, 0, -1),
      (2, -4, 0, -3, 5),
      (0, 0, -4, -4, -2),
      (-5, 3, -1, 3, -2),
      (-2, -3, 3, 1, -5)),
    DenseMatrix((-4, -5, -4, 0, 0),
      (-4, -2, -5, -4, -3),
      (-5, 5, -1, -4, -5),
      (0, 2, 4, 0, -2),
      (-1, -5, -4, 2, 0)),
    DenseMatrix((-3, -3, -1, 3, -2),
      (2, 2, 3, -1, -1),
      (1, -3, -1, 0, -5),
      (2, 2, 3, 2, 3),
      (2, 1, -2, 4, -3)),
    DenseMatrix((0, 3, -3, -4, -1),
      (0, -4, 1, 2, 5),
      (5, -2, 5, -2, 4),
      (-1, -4, 1, 3, 1),
      (-3, 0, -3, -1, 0)),
    DenseMatrix((-3, -5, -1, 3, 3),
      (3, 2, 4, -4, 1),
      (-4, -2, 2, -4, -1),
      (1, -5, -3, -4, 0),
      (-2, 4, 3, -5, -3)),
    DenseMatrix((2, -2, 3, 3, 0),
      (-5, 2, 3, -3, 2),
      (3, -1, 4, -2, 1),
      (4, 0, -2, 3, 5),
      (-2, 2, -3, -4, 3)),
    DenseMatrix((-1, 3, -3, 1, -3),
      (4, 5, -5, 0, -4),
      (3, 5, 3, -4, -4),
      (1, 0, 2, -2, 1),
      (-5, 5, 4, -1, -2)),
    DenseMatrix((0, 2, 3, -4, -1),
      (4, 3, 0, -4, -5),
      (4, 0, -4, 3, -1),
      (-5, -3, 2, -3, 2),
      (5, 3, 1, -3, 3)),
    DenseMatrix((4, 1, 2, -4, 5),
      (-4, 3, -4, 1, 4),
      (-2, -3, 1, 5, 4),
      (4, 0, -1, 5, -3),
      (1, -5, -3, -3, -5)),
    DenseMatrix((-2, 2, 4, -5, -2),
      (5, 1, 2, 1, 2),
      (-5, 1, 5, -3, -2),
      (-5, -1, -1, 2, 4),
      (-4, -4, -5, 2, 2))
  )

  val fc1 = DenseMatrix(Source.fromResource("fc1_400x120.data").getLines().toArray.head.split("_").map(_.toInt)).reshape(120, 400).t
  val fc2 = DenseMatrix(Source.fromResource("fc2_120x84.data").getLines().toArray.head.split("_").map(_.toInt)).reshape(84, 120).t
  val fc3 = DenseMatrix(Source.fromResource("fc3_84x10.data").getLines().toArray.head.split("_").map(_.toInt)).reshape(10, 84).t
}

