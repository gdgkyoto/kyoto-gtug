package hello;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class HelloTwitter {
  /**
   * Usage: java twitter4j.examples.GetTimelines ID Password
   * 
   * @param args
   *          String[]
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out
        .println("You need to specify TwitterID/Password combination to show UserTimelines.");
      System.out
        .println("Usage: java twitter4j.examples.GetTimelines ID Password");
      System.exit(0);
    }

    Twitter twitter = new Twitter(args[0], args[1]);

    List<Status> statuses;
    try {
      statuses = twitter.getMentions();
      System.out.println("------------------------------");
      System.out.println("Showing " + args[0] + "'s getMentions.");
      for (Status status : statuses) {
        System.out.println(status.toString().replaceAll(",", "\r\n"));
        System.out.println("------------------------------");
      }
    } catch (TwitterException te) {
      System.out.println("Failed to get timeline: " + te.getMessage());
    }
    // try {
    // statuses = twitter.getRetweetedToMe();
    // System.out.println("------------------------------");
    // System.out.println("Showing " + args[0] + "'s getRetweetedToMe.");
    // for (Status status : statuses) {
    // System.out.println(status.getUser().getName() + ":" + status.getText());
    // }
    // } catch (TwitterException te) {
    // System.out.println("Failed to get timeline: " + te.getMessage());
    // }
    // try {
    // statuses = twitter.getRetweetsOfMe();
    // System.out.println("------------------------------");
    // System.out.println("Showing " + args[0] + "'s getRetweetsOfMe.");
    // for (Status status : statuses) {
    // System.out.println(status.getUser().getName() + ":" + status.getText());
    // }
    // } catch (TwitterException te) {
    // System.out.println("Failed to get timeline: " + te.getMessage());
    // }

//    try {
//      twitter.updateStatus("テスト");
//    } catch (TwitterException te) {
//      System.out.println("Failed to get timeline: " + te.getMessage());
//    }
  }
}
