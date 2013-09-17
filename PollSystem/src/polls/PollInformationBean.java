package polls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PollInformationBean {
	private String pollId;
	private String adminId;
	private String title;
	private String description;
	private String initialName;
	private String email;
	private int pollType;
	private String ipAddress;
	private Timestamp pollCreateTime;
	private Timestamp updateTime;
	private Timestamp lastVoteTime;
	private boolean deleted;
	private int optionNum;
	private String[][] timeov;
	private List<Option> options = new ArrayList<Option>();
	
	public String getPollId() {
		return pollId;
	}
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInitialName() {
		return initialName;
	}
	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPollType() {
		return pollType;
	}
	public void setPollType(int pollType) {
		this.pollType = pollType;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String idAddress) {
		this.ipAddress = idAddress;
	}
	public Timestamp getPollCreateTime() {
		return pollCreateTime;
	}
	public void setPollCreateTime(Timestamp pollCreateTime) {
		this.pollCreateTime = pollCreateTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Timestamp getLastVoteTime() {
		return lastVoteTime;
	}
	public void setLastVoteTime(Timestamp lastVoteTime) {
		this.lastVoteTime = lastVoteTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}
	public int getOptionNum() {
		return optionNum;
	}
	public void setTimeov(String[][] timeov) {
		this.timeov = timeov;
	}
	public String[][] getTimeov() {
		return timeov;
	}
	
}
