//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Iterable Song Player
// Course: CS 300 Spring 2022
//
// Author: Matej Popovski
// Email: popovski@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * @author Matej Popovski
 *
 */
public class Song {
  private String songName; // name or title of the song
  private String artist; // artist of the song
  private String duration; // duration of the song

  /**
   * Makes a new Song given the song name, the name of the artist, and the duration of the song
   * 
   * @param songName - title of the song
   * @param artist   - name of the artist who performed this song
   * @param duration - duration of the song in the format mm:ss
   *
   * @throws IllegalArgumentException - with a descriptive error message if either of songName, or
   *                                  artist, or duration is null or is blank, or if the duration is
   *                                  not formatted as mm:ss where both mm and ss are in the 0 .. 59
   *                                  range.
   */
  public Song(String songName, String artist, String duration) {
    try {
      String[] splitter = duration.split(":");

      if (songName == null || songName.equals("") || artist == null || artist.equals("")
          || (Integer.parseInt(splitter[0]) % 60 != Integer.parseInt(splitter[0]))
          || (Integer.parseInt(splitter[1]) % 60 != Integer.parseInt(splitter[1]))) {
        throw new IllegalArgumentException("ERROR: Duration is invalid.");
      }
    } catch (Exception e) {
      System.out.println("ERROR: Invalid duration format");
    }
    this.songName = songName;
    this.artist = artist;
    this.duration = duration;
  }

  /**
   * Returns a string representation of this song. This string should be formatted as follows.
   * "songName---artist---duration" where songName is the title of the song, artist is the name of
   * the artist, and duration is the duration of the song.
   *
   * @return a string representation of this song.
   */
  @Override
  public String toString() {
    return this.songName + "---" + this.artist + "---" + this.duration;
  }

  /**
   * Gets the name of the artist who performed this song
   *
   * @return the artist who performed this song
   */
  public String getArtist() {
    return this.artist;
  }

  /**
   * Gets the duration of this song
   *
   * @return the duration
   */
  public String getDuration() {
    return this.duration;
  }


  /**
   * Returns true when this songs name and artist strings equals to the other songs name and artist
   * strings, and false otherwise. Note that this method takes an Object rather than a Song
   * argument, so that it Overrides Object.equals(Object). If an object that is not an instance of
   * Song is ever passed to this method, it should return false.
   * 
   * @param other - Song object to compare this object to
   *
   * @return true when the this song has matching name and artist with respect to another song (case
   *         insensitive comparison)
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other instanceof Song) {
      Song otherSong = (Song) other;
      if (otherSong.getArtist().equalsIgnoreCase(this.getArtist())
          && otherSong.getSongName().equalsIgnoreCase(this.getSongName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the title or name of this song
   *
   * @return the title or name of this song
   */
  public String getSongName() {
    return this.songName;
  }
}
