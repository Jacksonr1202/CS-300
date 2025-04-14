//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Playlist Generator Tester
// Course:   CS 300 Spring 2025
//
// Author:   Jackson Ryan
// Email:    jdryan7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x__ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:   Hobbes lectures, general recursion concepts
// Online Sources:  /https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
// Helped me understand general permutations algorithm
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * A class to test the functionality of the {@code Playlist} and {@code PlaylistGenerator} classes.
 * It includes tests for simple, permutation-based, and optimal playlist generation methods.
 */
public class PlaylistTester {
  /**
   * Tests the base cases for the simple playlist generator. Ensures that an empty song list and a
   * playlist already at max duration are handled correctly.
   *
   * @return true if all base cases pass, false otherwise
   */
  public static boolean simpleGeneratorBaseCaseTest() {
    // TODO Base Case 1: Empty song list
    ArrayList<Song> emptySongList = new ArrayList<>();
    Playlist p = new Playlist();
    Playlist newPlay = p.addSong(new Song("s", "c", 10));
    int dur = 10;
    Playlist resP = PlaylistGenerator.simplePlaylist(emptySongList, newPlay, 20);
    System.out.println(resP.getTotalDuration());
    if (resP.getTotalDuration() != dur) {
      return false;
    }
    // TODO Base Case 2: Existing playlist already meets max duration
    Playlist testPlaylist = new Playlist();
    Song newSong = new Song("Revival", "Country", 120);
    Playlist newPlaylist = testPlaylist.addSong(newSong);
    ArrayList<Song> testSongList = Utilities.generateRandomSongs(100);
    Playlist resultPlaylist = PlaylistGenerator.simplePlaylist(testSongList, newPlaylist, 120);
    if (resultPlaylist.getSongs().get(0).getTitle().equals("Revival") && resultPlaylist.size() == 1) {
      return true;
    }
    return false; // default return statement
  }

  /**
   * Tests the simple playlist generator with one song adding to a non-empty playlist. Ensures that
   * a song fitting within the duration limit is added, and one exceeding the limit is not added.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean simpleGeneratorOneStepTest() {
    // TODO Case 1: One song that fits
    Playlist testPlaylist = new Playlist();
    Song newSong = new Song("Revival", "Country", 120);
    Playlist newPlaylist = testPlaylist.addSong(newSong);
    ArrayList<Song> testSongList = Utilities.generateRandomSongs(100);
    Song songAdd = new Song("secondSong", "Country", 2);
    testSongList.set(0, songAdd);
    Playlist resultPlaylist = PlaylistGenerator.simplePlaylist(testSongList, newPlaylist, 130);
    if (resultPlaylist.getSongs().get(0).getTitle().equals("Revival") && resultPlaylist.size() == 2 &&
        resultPlaylist.getSongs().get(1).getTitle().equals("secondSong")) {
      return true;
    }

    // TODO Case 2: One song that does not fit
    Song notFit = new Song("thesong", "country", 12);
    testSongList.set(0, notFit);
    Playlist finalResult = PlaylistGenerator.simplePlaylist(testSongList, resultPlaylist, 130);
    if (finalResult.getSongs().get(0).getTitle().equals("Revival") && finalResult.size() == 2 &&
        finalResult.getSongs().get(1).getTitle().equals("secondSong")) {
      return true;
    }
    return false; // default return statement
  }

  /**
   * Tests the recursive functionality of the simple playlist generator. Ensures that multiple songs
   * are added without exceeding the duration limit.
   *
   * @return true if the recursive addition works correctly, false otherwise
   */
  public static boolean simpleGeneratorRecursiveTest() {
    // TODO Test Case: Multiple songs, ensuring correct recursive addition
    ArrayList<Song> songList = new ArrayList<>();
    Song song1 = new Song("SongOne", "Country", 100);
    Song song2 = new Song("SongTwo", "Country", 20);
    Song song3 = new Song("SongThree", "Country", 20);
    Song song4 = new Song("SongFour", "Country", 20);
    songList.add(song1);
    songList.add(song2);
    songList.add(song3);
    songList.add(song4);
    Playlist initPlaylist = new Playlist();
    Playlist resultPlaylist = PlaylistGenerator.simplePlaylist(songList, initPlaylist, 60);
    if (resultPlaylist.size() == 3 && resultPlaylist.getSongs().get(0).equals(song2) &&
        resultPlaylist.getSongs().get(1).equals(song3)) {
      return true;
    }
    return false; // default return statement
  }

  /**
   * Tests the permutation generation method for song lists. Verifies that all permutations are
   * generated correctly. You may consider checking the size of results and the size of each
   * permutation in results.
   *
   * @return true if this tester verifies a correct functionality, false otherwise
   */
  public static boolean generatePermutationsTest() {
    // TODO Case 1: empty song list
    ArrayList<Song> songList = new ArrayList<>();
    ArrayList<ArrayList<Song>> resultList = new ArrayList<>();
    PlaylistGenerator.generatePermutations(songList, 0, resultList);
    if (!resultList.isEmpty()) {
      return false;
    }
    // TODO Case 2: normal case (not empty song list)
    Song song1 = new Song("Song 1", "Country", 100);
    Song song2 = new Song("Song 2", "Rap", 200);
    ArrayList<Song> newSongList = new ArrayList<>();
    newSongList.add(song1);
    newSongList.add(song2);
    PlaylistGenerator.generatePermutations(newSongList, 0, resultList);
    ArrayList<Song> expected1 = new ArrayList<>();
    expected1.add(song1);
    expected1.add(song2);
    ArrayList<Song> expected2 = new ArrayList<>();
    expected2.add(song1);
    expected2.add(song2);
    if (resultList.contains(expected1) && resultList.contains(expected2) && resultList.size() == 2) {
      return true;
    }
    return false; // default return statement
  }

  /**
   * Tests the permutation-based playlist generation method. Ensures that multiple songs (at least
   * three) are permuted and the best playlist is selected without exceeding the maximum duration.
   *
   * @return true if the permutation-based playlist is generated correctly, false otherwise
   */
  public static boolean bestPermutationPlaylistRecursiveTest() {
    // TODO Case: Multiple songs, ensuring permutations are checked
    ArrayList<Song> songs = new ArrayList<>();
    songs.add(new Song("Song1", "Country", 2));
    songs.add(new Song("Song2", "Country", 3));
    songs.add(new Song("Song3", "Country", 7));
    int maxDuration = 12;
    Playlist p = PlaylistGenerator.bestPermutationPlaylist(songs, maxDuration);
    if (p.getTotalDuration() == 12) {
      return true;
    }
    return false; // default return statement
  }

  /**
   * Tests the optimal playlist generation with base cases. Ensures correct handling of empty song
   * lists and playlists already at max duration.
   *
   * @return true if base cases are handled correctly, false otherwise
   */
  public static boolean optimalPlaylistBaseCaseTest() {
    // TODO Base Case 1: Empty song list
    {
      ArrayList<Song> emptySongList = new ArrayList<>();
      Playlist emptyPlaylist = new Playlist();
      Playlist result = PlaylistGenerator.optimalPlaylist(emptySongList, emptyPlaylist, 15);
      if (result == null || result.getTotalDuration() != 0) {
        return false;
      }
    }
    // TODO Base Case 2: Existing playlist already meets max duration
    ArrayList<Song> songs = new ArrayList<>();
    songs.add(new Song("S1", "Rap", 10));
    songs.add(new Song("S2", "Rap", 15));
    songs.add(new Song("S3", "Rap", 20));
    Playlist p = new Playlist();
    p.addSong(new Song("S", "Rap", 45));
    Playlist result = PlaylistGenerator.optimalPlaylist(songs, p, 45);
    if (result == null || result.getTotalDuration() != 45) {
      return false;
    }
    return true; // default return statement
  }

  /**
   * Tests the optimal playlist generation method with one-step cases adding to a nonempty playlist.
   * Ensures that a song fitting within the maximum duration is added, and a song exceeding the
   * limit is not added.
   *
   * @return true if the optimal playlist handles one-step cases correctly, false otherwise
   */
  public static boolean optimalPlaylistOneStepTest() {
    // TODO Case 1: One song that fits{
    {
      ArrayList<Song> oneSong = new ArrayList<>();
      oneSong.add(new Song("s", "c", 10));
      Playlist p = new Playlist();
      int maxDur = 20;
      Playlist result = PlaylistGenerator.optimalPlaylist(oneSong, p, maxDur);
      if (result.getTotalDuration() != 10) {
        return false;
      }
    }
    // TODO Case 2: One song that does not fit
    {
      ArrayList<Song> oneSong = new ArrayList<>();
      oneSong.add(new Song("s", "c", 10));
      Playlist p = new Playlist();
      int maxDur = 5;
      Playlist result = PlaylistGenerator.optimalPlaylist(oneSong,p,maxDur);
      if(result.getTotalDuration() != 0){
        return false;
      }
    }
    return true; // default return statement
  }

  /**
   * Tests the optimal playlist generation method with multiple songs. Ensures that recursive
   * backtracking selects the best playlist without exceeding the maximum duration.
   *
   * @return true if the optimal playlist is generated correctly, false otherwise
   */
  public static boolean optimalPlaylistRecursiveTest() {
    // TODO Case: Multiple songs, ensuring permutations are checked
    ArrayList<Song> songs = new ArrayList<>();
    songs.add(new Song("s1","r",10));
    songs.add(new Song("s2","r",7));
    songs.add(new Song("s3","r",13));
    songs.add(new Song("s4","r",19));
    int maxDur = 32;
    Playlist p = new Playlist();

    Playlist res = PlaylistGenerator.optimalPlaylist(songs,p,maxDur);
    if(res.getTotalDuration() == 32){
      return true;
    }
    return false; // default return statement
  }

  /**
   * The main method runs all test cases for the playlist generator.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "simpleGeneratorBaseCaseTest: " + (simpleGeneratorBaseCaseTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "simpleGeneratorOneStepTest: " + (simpleGeneratorOneStepTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "simpleGeneratorRecursiveTest: " + (simpleGeneratorRecursiveTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "generatePermutationsTest: " + (generatePermutationsTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("bestPermutationPlaylistRecursiveTest: " + (bestPermutationPlaylistRecursiveTest() ?
        "Pass" :
        "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "optimalPlaylistBaseCaseTest: " + (optimalPlaylistBaseCaseTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "optimalPlaylistOneStepTest: " + (optimalPlaylistOneStepTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "optimalPlaylistRecursiveTest: " + (optimalPlaylistRecursiveTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
  }


}
