//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Playlist Generator
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
 * A utility class for generating playlists based on different strategies. This class provides
 * methods to generate playlists using simple, permutation-based, and optimal backtracking
 * approaches.
 */
public class PlaylistGenerator {

  /**
   * RECURSIVE method: Generates a simple playlist by adding the first available songs until the
   * maximum duration is reached.
   *
   * @param songs       the list of available songs
   * @param playlist    the current playlist being generated
   * @param maxDuration the maximum allowed duration for the playlist
   * @return a playlist containing songs that fit within the specified duration
   */
  public static Playlist simplePlaylist(ArrayList<Song> songs, Playlist playlist, int maxDuration) {
    if(songs.isEmpty() || playlist.getTotalDuration() == maxDuration){
      return playlist;
      //Base case
    }
    Song currSong = songs.get(0);
    songs.remove(0);
    if (!playlist.canAddSong(currSong, maxDuration)) {
      return simplePlaylist(songs, playlist, maxDuration);
      //Assures we can add a song
    }
    Playlist updatedPlaylist = playlist.addSong(currSong);
    return simplePlaylist(songs, updatedPlaylist, maxDuration);
  }

  /**
   * RECURSIVE method: Generates all permutations of the given song list.
   *
   * @param songs  the list of songs to permute
   * @param index  the current index for generating permutations
   * @param result the list to store ALL the generated permutations
   */
  public static void generatePermutations(ArrayList<Song> songs, int index,
                                          ArrayList<ArrayList<Song>> result) {
    if (songs.isEmpty() || index < 0 || index >= songs.size()) {
      return;
      //Base case
    }
    if (index == songs.size() - 1) {
      result.add(new ArrayList<>(songs));
    } else {
      for (int i = index; i < songs.size(); i++) {
        Song temp = songs.get(index);
        songs.set(index, songs.get(i));
        songs.set(i, temp);
        //Traverse down branch of tree
        //Shifting letters around
        generatePermutations(songs, index + 1, result);
        temp = songs.get(i);
        songs.set(i, songs.get(index));
        songs.set(index, temp);
      }
    }
  }

  /**
   * Generates the best possible playlist by evaluating all permutations of the song list. It
   * selects the permutation that maximizes the total playlist duration without exceeding the
   * limit.
   *
   * @param songs       the list of available songs
   * @param maxDuration the maximum allowed duration for the playlist
   * @return the best possible playlist based on all song permutations
   */
  public static Playlist bestPermutationPlaylist(ArrayList<Song> songs, int maxDuration) {
    ArrayList<ArrayList<Song>> songPerms = new ArrayList<>();
    generatePermutations(songs, 0, songPerms);
    int longDuration = 0;
    Playlist bestPlaylist = null;
    for (int i = 0; i < songPerms.size(); i++) {
      Playlist p = simplePlaylist(new ArrayList<>(songPerms.get(i)),
          new Playlist(),maxDuration);
      if (p.getTotalDuration() > longDuration) {
        bestPlaylist = p;
        longDuration = p.getTotalDuration();
        //If playlist has longer duration then
        //current longest duration set that to
        //the best playlist
      }
    }
    return bestPlaylist; // default return statement
  }


  /**
   * RECURSIVE method: Generates an optimal playlist using a backtracking approach to maximize the
   * total duration while staying within the maximum allowed duration.
   *
   * @param songs       the list of available songs
   * @param playlist    the current playlist being generated
   * @param maxDuration the maximum allowed duration for the playlist
   * @return the optimal playlist with the maximum possible duration based on the backtracking
   * approach
   */
  public static Playlist optimalPlaylist(ArrayList<Song> songs, Playlist playlist,
                                         int maxDuration) {
    if (songs.isEmpty() || playlist.getTotalDuration() == maxDuration) {
      return playlist;
      //Base case
    }
    Song s = songs.get(0);
    ArrayList<Song> songsSub = new ArrayList<>(songs.subList(1, songs.size()));
    Playlist skipFirst = optimalPlaylist(songsSub,playlist,maxDuration);
    Playlist fullPlaylist = playlist;
    if (fullPlaylist.canAddSong(s, maxDuration)) {
      Playlist newPlaylist = playlist.addSong(s);
      fullPlaylist = optimalPlaylist(songsSub,newPlaylist,maxDuration);
      //If we can add the song recurse
    }
    if (fullPlaylist.getTotalDuration() > skipFirst.getTotalDuration()) {
      return fullPlaylist;
    } else {
      return skipFirst;
      //Choose best playlist
    }
  }
}


