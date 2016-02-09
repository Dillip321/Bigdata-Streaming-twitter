import java.io.FileWriter;
import java.io.IOException;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class SimpleStream {

	public static void main(String[] args) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		
		//put your own consumer keys
		cb.setOAuthConsumerKey("wft8UtjC7c6fATTMH8EtNw");
		cb.setOAuthConsumerSecret("88aFIBLy80sVaV3yHAbc7QvTuKXkBenYNc8yn2nZHLM");
		cb.setOAuthAccessToken("1883532464-vKve85BRvaco1rBMqsXHaJKOBeoYdDAG0tZTxZU");
		cb.setOAuthAccessTokenSecret("GGoPxGgNMMdHElZrUhIItKpttVbIg8d9COa1Utbig");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();

		StatusListener listener = new StatusListener() {
			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStatus(Status status) {
				User user = status.getUser();
				//you can add more fieldshere if you want more imformation from tweets
				JSONObject tweets = new JSONObject();
				try {
					tweets.putOnce("tweetId", status.getId());
					tweets.putOnce("location", user.getLocation());
					tweets.putOnce("username", status.getUser().getScreenName());
					tweets.putOnce("content", status.getText());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					//change the location for storing the json eg. new FileWriter("c:\\test.json");
					FileWriter file = new FileWriter("C:\\test.json");
					file.write(tweets.toString());
					file.flush();
					file.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println(tweets);

			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

		};
		
		FilterQuery fq = new FilterQuery();
		
		//put the filter keyword you want in your tweets
		String keywords[] = { "india" };
		fq.track(keywords);
		twitterStream.addListener(listener);
		twitterStream.filter(fq);
		fq.toString();

	}
}
