Sudoku App (See all code at https://github.com/waderoberts123/CS300_Final_Project)

Main Method
-----------
SudokuGameEngine.main()


Purpose
-------
This app has two purposes, to give users a library to 400 sudoku puzzles and solutions to be able to select at random,
and to solve any valid sudoku puzzle any user has to offer.


Commands to Use the Program
---------------------------
The menu options are quite self-explanatory, but below are the options in the main menu. Simply enter the integer
corresponding to the menu option you would like, and then follow the prompts listed in the menu option.

Menu Options
    1) Select a random puzzle to solve.
    2) Find the solution to a puzzle.
    3) Quit.


Limitations That Would Cause the Program to Crash
-------------------------------------------------
Some of the obvious limitations are:
    1) Library file is not formatted properly will cause uncaught exceptions (somewhat intentional).
    2) If reading the library file fails for any reason, the program will crash.
    3) If the example puzzle in SudokuGameEngine.findSolutionToUserPuzzle() somehow gets corrupted it will cause the
    program to crash.

Which Option I Chose and Which Class Implements This Feature
------------------------------------------------------------
I chose option c, to use recursion in a meaningful way in the program. You can see this implemented in
SudokuPuzzle.solvePuzzle().


Note on Testing
---------------
The -ea flag needs to be enabled in the SudokuTester class in order to see assertion exceptions, however, there should
not be any of those since all test cases are passing.
