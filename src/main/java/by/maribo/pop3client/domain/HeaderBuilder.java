package by.maribo.pop3client.domain;

public class HeaderBuilder {
	private static final String DATE = "Date";
	private static final String FROM = "From";
	private static final String SUBJECT = "Subject";
	private String date;
	private String from;
	private String subject;

	public HeaderBuilder() {
	}

	public void addToHeader(String name, String value) {
		if (name.equals(DATE)) {
			date = value;
		} else if (name.equals(FROM)) {
			from = value;
		} else if (name.equals(SUBJECT)) {
			subject = value;
		}
	}

	public Message.Header buildHeader() {
		Message.Header header = new Message.Header();
		header.setDate(date);
		header.setFrom(from);
		header.setSubject(subject);
		return header;
	}
}
