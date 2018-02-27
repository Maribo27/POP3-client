package by.maribo.pop3client.domain;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
	private Header header;
	private String body;
	private int number;

	public Message() {
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Message message = (Message) o;
		return number == message.number &&
				Objects.equals(header, message.header) &&
				Objects.equals(body, message.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(header, body, number);
	}

	@Override
	public String toString() {
		return "Message:" +
				"\n\tnumber : " + number +
				header +
				"\tbody : " + body;
	}

	public static class Header implements Serializable{
		private String date;
		private String from;
		private String subject;

		Header() {
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Header header = (Header) o;
			return Objects.equals(date, header.date) &&
					Objects.equals(from, header.from) &&
					Objects.equals(subject, header.subject);
		}

		@Override
		public int hashCode() {
			return Objects.hash(date, from, subject);
		}

		@Override
		public String toString() {
			return "\n\tdate : " + date +
					"\tfrom : " + from +
					"\tsubject : " + subject;
		}
	}
}
