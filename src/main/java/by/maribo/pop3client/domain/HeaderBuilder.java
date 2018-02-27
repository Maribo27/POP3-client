package by.maribo.pop3client.domain;

public class HeaderBuilder {
	private String date;
	private String from;
	private String subject;

	public HeaderBuilder() {
	}

	public void addToHeader(String name, String value) {
		if (name.equals("Date")) {
			date = value;
		} else if (name.equals("From")) {
			from = value;
		} else if (name.equals("Subject")) {
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
