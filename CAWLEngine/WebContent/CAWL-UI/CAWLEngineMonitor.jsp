<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CAWL Engine Monitor</title>
		<style>
			.scrolltbody {
				display: block;
				height: 300px;
				overflow: auto;
				text-align: center;
				background-color: #F0E5DE;
				font-size: 1.0em;
			}
			.scrolltbody td {
				border: 0.5px solid #CC3D3D;
			}
			.fileLabel {
				background: #C65146;
				display: table;
				color: white;
				padding: 1px;
				font-size: 1.3em;
				width: 140px;
				border: none;
			}
			.fileHidden {
				display: none;
			}
			.controlButton {
				background: #C65146;
				display: table;
				color: white;
				padding: 1px;
				font-size: 1.1em;
				border: none;
				width: 180px;
			}
			.controlStopButton {
				background: #C65146;
				display: table;
				color: white;
				padding: 1px;
				font-size: 1.1em;
				border: none;
				width: 100px;
				height: 43px;
			}
		</style>
		<script type="text/javascript">
			var taskToggle = false;
			
			var webSocket = new WebSocket("ws://203.253.23.46:8080/CAWLEngine/Controller");
			
			webSocket.onopen = function(message) {
	        };
	        
	        webSocket.onclose = function(message) {
	        };
	        
	        webSocket.onerror = function(message) {
	        };
	        
	        webSocket.onmessage = function(message) {
	        	if(message.data == "") {
	        		return;
	        	}
	        	
	        	var jsonText = message.data;
        		var jsonContact = JSON.parse(jsonText);
	        	
        		switch(jsonContact.key) {
	        		case "cawlNodes":
	        			for(var i = 0; i < jsonContact.nodes.length; i++) {
	        				var node = jsonContact.nodes[i];
	        				
	        				var row = dynamicTbody.insertRow(dynamicTbody.rows.length);
	        				var cell0 = row.insertCell(0);
	        				var cell1 = row.insertCell(1);
	        				var cell2 = row.insertCell(2);
	        				var cell3 = row.insertCell(3);
	        				
	        				cell0.innerHTML = node.node;
			        		cell1.innerHTML = node.documentation;
			        		cell2.innerHTML = node.condition;
			        		cell3.innerHTML = node.service;
	        			}
	        			break;
	        		case "cawlState":
	        			if(jsonContact.cawlNodes == "empty") {
	        				for(var i = 0; i < dynamicTbody.rows.length; i++) {
	        					dynamicTbody.rows[i].style.backgroundColor = "#F5D3D6";
	        				}
	        			}
	        			
	        			if(jsonContact.cawlNodes != "") {
	        				for(var i = 0; i < dynamicTbody.rows.length; i++) {
	        					dynamicTbody.rows[i].style.backgroundColor = "#F5D3D6";
	        				}
	        				
	        				var cawlNodesText = jsonContact.cawlNodes;
	        				var cawlNodesTextArr = cawlNodesText.split(',');
	        				
	        				for(var i = 0; i < cawlNodesTextArr.length; i++) {
	        					for(var j = 0; j < dynamicTbody.rows.length; j++) {
	        						if(cawlNodesTextArr[i] == dynamicTbody.rows[j].cells[0].firstChild.nodeValue) {
	        							dynamicTbody.rows[j].style.backgroundColor = "#E57373";
	        						}
	        					}
	        				}
	        			}
	        			
	        			if(jsonContact.systemLog != "") {
	        				var systemLogText = jsonContact.systemLog;
	        				var systemLogTextArr = systemLogText.split(',');
	        				
	        				for(var i = 0; i < systemLogTextArr.length; i++) {
	        					systemLogTextarea.value += systemLogTextArr[i] + "\n";
	        				}
	        			}
	        			break;
        			default:
        				break;
        		}
	        };
	        
	        function disconnect() {
	        	webSocket.close();
	        }
	        
	        function fileChange() {
	        	fileName.value = filePath.value;
	        }
	        
	        function workflowStart() {
	        	var file = document.getElementById("filePath").files[0];
	        	webSocket.send("{\"key\":\"fileUploadStart\",\"fileName\":\"" + file.name + "\"}");
	        	
	        	var reader = new FileReader();
	        	var rawData = new ArrayBuffer();
	        	
	        	reader.onload = function(e) {
	        		rawData = e.target.result;
	        		webSocket.send(rawData);
	        		webSocket.send("{\"key\":\"fileUploadEnd\"}");
	        	}
	        	
	        	reader.readAsArrayBuffer(file);
	        	
	        	workflowState.value = "Running";
	        }
	        
	        function workflowStop() {
	        	webSocket.send("{\"key\":\"workflowStop\"}");
	        	workflowState.value = "Finish";
	        }
	        
	        function taskControll() {
	        	webSocket.send("{\"key\":\"workflowTaskControll\"," + "\"taskControll\":" + taskToggle + "}");
	        	
	        	if(taskToggle == false) {
	        		workflowState.value = "Stopping";
	        		document.getElementById("taskButton").value = "Task Run";
	        		taskToggle = true;
	        	} else {
	        		workflowState.value = "Running";
	        		document.getElementById("taskButton").value = "Task Stop";
	        		taskToggle = false;
	        	}
	        }
	        
	        setInterval(function displayRefresh() {
	        	webSocket.send("{\"key\":\"stateCheck\"}");
	        }, 1000)
		</script>
	</head>
	<body>
		<center>
			<br>
			<font size="10"><B>CAWL Engine Monitor</B></font>
			<br><br><br>
			<table width="1400px">
				<thead>
					<tr>
						<th colspan="2" width="600px" bgcolor="#52616a">
							<font size="5" color="white">CAWL Scenario Document</font>
						</th>
						<th width="200px">
						</th>
						<th colspan="3" width="600px" bgcolor="#52616a">
							<font size="5" color="white">CAWL Engine Controller</font>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td rowspan="2" width="430px" bgcolor="#c9d6de">
							<center><input type="text" id="fileName" size="40" style="height:25px; width:380px; text-align:center; font-size:100%;" readonly></center>
						</td>
						<td rowspan="2" width="170px" bgcolor="#c9d6de">
							<center>
								<label class="fileLabel"> File Select
									<input type="file" id="filePath" class="fileHidden" onchange="fileChange()">
								</label>
							</center>
						</td>
						<td rowspan="2" width="200px">
						</td>
						<td rowspan="2" width="250px" bgcolor="#c9d6de">
							<center><input type="text" id="workflowState" size="18" style="height:25px; width:200px; text-align:center; font-size:140%;" readonly></center>
						</td>
						<td width="220px" bgcolor="#c9d6de">
							<center><input type="button" value="     Workflow Start     " class="controlButton" onClick="workflowStart()"></center>
						</td>
						<td rowspan="2" width="130px" bgcolor="#c9d6de">
							<center><input type="button" id="taskButton" value=" Task Stop " class="controlStopButton" onClick="taskControll()"></center>
						</td>
					</tr>
					<tr>
						<td width="220px" bgcolor="#c9d6de">
							<center><input type="button" value="     Workflow Finish     " class="controlButton" onclick="workflowStop()"></center>
						</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<table width="1400px">
				<thead>
					<tr>
						<th bgcolor="#548687">
							<font size="5" color="white">Workflow Node List</font>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<table class="scrolltbody">
								<thead>
									<tr height="30px" bgcolor="#8FBC94">
										<th width="350px">Node</th>
										<th width="350px">Documentation</th>
										<th width="350px">Condition</th>
										<th width="350px">Service</th>
									</tr>
								</thead>
								<tbody id="dynamicTbody">
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<table width="1400px">
				<thead>
					<tr>
						<th bgcolor="#7C7877">
							<font size="5" color="white">System Log</font>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td bgcolor="#D9D4CF">
							<center><textarea id="systemLogTextarea" cols="190" rows="20" readonly></textarea></center>
						</td>
					</tr>
				</tbody>
			</table>
		</center>
	</body>
</html>