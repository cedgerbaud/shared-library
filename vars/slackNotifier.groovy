#!/usr/bin/env groovy

blocks = [
  [
    "type": "section",
    "text": [
			"type": "mrkdwn",
			"text": "<a href='https://www.thalesgroup.com/en'><img src='https://img.shields.io/badge/Thales-100%25-blue.svg?style=for-the-badge&logo=airbus'/></a>CONGRATULATION: A320 est en route, vive Thales !"
		]
  ],
  [
    "type": "divider"
  ],
  [
    "type": "section",
    "text": [
			"type": "mrkdwn",
			"text": "Décollage imminent"
		],
    "accessory": [
			"type": "image",
			"image_url": "https://media3.giphy.com/media/5XCWLH6ovlsiISpU2U/giphy.gif",
			"alt_text": "take off"
		]
  ],
]

def call(String buildResult) {
  if ( buildResult == "SUCCESS" ) {
    slackSend(channel: "test_notif_jenkins", blocks: blocks),
  }
  else if( buildResult == "FAILURE" ) { 
    slackSend color: "danger", message: "BAD NEWS:Job ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was failed ! more info ${env.BUILD_URL}"
  }
  else if( buildResult == "UNSTABLE" ) { 
    slackSend color: "warning", message: "BAD NEWS:Job ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was unstable ! more info ${env.BUILD_URL}"
  }
  else {
    slackSend color: "danger", message: "BAD NEWS:Job ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} its result was unclear ! more info ${env.BUILD_URL}"	
  }
}
