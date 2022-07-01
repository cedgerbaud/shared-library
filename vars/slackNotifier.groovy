#!/usr/bin/env groovy

// blocks = [
//   [
//     "type": "section",
//     "text": [
// 			"type": "mrkdwn",
// 			"text": "<a href='https://www.thalesgroup.com/en'><img src='https://img.shields.io/badge/Thales-100%25-blue.svg?style=for-the-badge&logo=airbus'/></a>CONGRATULATION: A320 est en route, vive Thales !"
// 		]
//   ],
//   [
//     "type": "divider"
//   ],
//   [
//     "type": "section",
//     "text": [
// 			"type": "mrkdwn",
// 			"text": "Décollage imminent"
// 		],
//     "accessory": [
// 			"type": "image",
// 			"image_url": "https://media3.giphy.com/media/5XCWLH6ovlsiISpU2U/giphy.gif",
// 			"alt_text": "take off"
// 		]
//   ],
// ]

// def payload = JsonOutput.toJson([
//       "username": "Production Deployer",
//       "icon_emoji": ":robot_face:",
//       "mrkdwn": true,
//       "attachments": [
//          [
//             "mrkdwn_in": ['text','pretext'],
//             "color": "#199515",
//             "text": "*$JOB_NAME:* <$BUILD_URL|Build #$BUILD_NUMBER>, _microservice_ in _${clusterName}_ successfully updated.",
//             "fallback": "*Production Deployer*: operation succeeded."
//         ]
//       ]
//    ])

def call(String buildResult) {
  if ( buildResult == "SUCCESS" ) {
    slackSend color: "good", message: "CONGRATULATION: 1er décollage de l'A320 ! more info ${env.BUILD_URL}"
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
