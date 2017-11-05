#!Groovy

/*
    Pretty straightforward. You can combine with parallel steps to speed things up.
    You could also have a many repo -> one build job by pushing the vars in the notification.
*/

scm_branch = ''
scm_creds = ''
scm_url = ''

container_org = ''
container_name = ''
container_tag = 'latest'

node() {
  stage 'Checkout'
      git branch: scm_branch, credentialsId: scm_creds, url: scm_url

      def active_container = docker.image("${container_org}/${container_name}:${container_tag}");

  //stage 'Pull Container'
  //    active_container.pull()

  stage 'Build Containers'
      def active_containerImg = docker.build("${container_org}/${container_name}:${container_tag}", '.');

  stage 'Push Containers'
      active_containerImg.push()
}
