package polls;


public class Option {

	private String optionContent;
	private String pollId ;
	private int id;

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public String getOptionContent() {
		return optionContent;
	}
	
}
