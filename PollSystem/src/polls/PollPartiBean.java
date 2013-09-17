package polls;

import java.util.ArrayList;
import java.util.List;

public class PollPartiBean {
	private String bestOption = new String("还没人参加投票");
	private List<PollMember> members = new ArrayList<PollMember>();
	private String pollId = new String();
	private int[][] pollResult = null;
	private int[] choose = null;
	private String[][] comments = null;
	private int commentNum = 0;
	
	public String getBestOption() {
		return bestOption;
	}
	public void setBestOption(String bestOption) {
		this.bestOption = bestOption;
	}

	public List<PollMember> getMembers() {
		return members;
	}
	public void setMembers(List<PollMember> members) {
		this.members = members;
	}
	public String getPollId() {
		return pollId;
	}
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
	public void setPollResult(int[][] pollResult) {
		this.pollResult = pollResult;
	}
	public int[][] getPollResult() {
		return pollResult;
	}
	public void setChoose(int[] choose) {
		this.choose = choose;
	}
	public int[] getChoose() {
		return choose;
	}
	public void setComments(String[][] comments) {
		this.comments = comments;
	}
	public String[][] getComments() {
		return comments;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getCommentNum() {
		return commentNum;
	}

}
