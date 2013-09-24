<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
    var calendar;
	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
	    calendar = $('#calendar').fullCalendar({
			header: {
				left: 'prev,next',
				center: 'title',
				right: 'month,agendaWeek'
			},
			defaultView: 'agendaWeek',
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
					calendar.fullCalendar('renderEvent',
						{
						    id: $('#eventId').val(),
							title: '${meeting.title}',
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				 var id= $('#eventId').val()+1;
				 $('#eventId').attr('value',id);
				calendar.fullCalendar('unselect');
			},
			editable: true,
			events: [
			],
			eventClick: function(calEvent, jsEvent, view) {
				calendar.fullCalendar('removeEvents',calEvent.id);
		    }
		     
		});

	});
	function  getAllEvents()
		{
			  var evts=calendar.fullCalendar('clientEvents');
			  var  i, len = evts.length;
			  var json="{\"timeSlotJsons\":[";
			  for (i=0; i<len; i++) {
					e = evts[i];
				    if(i!=0)
				    {
				    	  json=json+",";
				    }
					json = json +"{\"start\":\""+e.start+"\",\"end\":\""+e.end+"\"}";
			   }
			  json = json +"]}";
			 // alert(json);
			  $('#eventsJson').attr('value',json);
		}

</script>
 <p><span class="special1">安排一个活动</span></p>
 <p><span class="special3"><div style="display:inline;">1. 概况 </div> >> <div style="font-weight: bold;color: #3399FF;display:inline;">2. 选择时间    </div> >> 3. 发送邀请</span></p>
 <fieldset>
 <legend>请选择日期以及时间:</legend>
 <form:form method="post" modelAttribute="meeting">
 <div id='calendar'></div>
 <input type="hidden" name="step2" />
 <input type="hidden" id="eventId"  value="0" />
 <form:input type="hidden" id="eventsJson" path="eventsJson"/>
 <form:input type="hidden" path="title"/>
 <form:input type="hidden" path="location"/>
 <form:input type="hidden" path="description"/>
 <form:input type="hidden" path="creator"/>
 <form:input type="hidden" path="creatorEmail"/>
 <p><input type="submit" name="cancel" value="取消" /><input type="submit" onclick="getAllEvents();" value="下一步" /></p>
 
</form:form>
 </fieldset>
