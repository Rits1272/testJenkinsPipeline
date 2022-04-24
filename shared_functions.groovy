def bumpVersionXmlFile() {
  def parser = getXmlParser()
  def xmlFile = new File('/Users/ritik/Info.plist')
  def parse = parser.parse(xmlFile)
  String newVersion = (Float.valueOf(parse.dict.string[7].text()) + 1.0).toString
  parse.dict.string[7].value = newVersion
  parse.dict.string[8].value = newVersion
  return XmlUtil.serialize(parse)
}

def bump_bundle_version_and_create_pr_to_ios_enterprise_app(String old_version, String new_version) {
    env.branch_name = "AL_4809" + "_" + "bump_CFBundleVersion"
    env.message = "bumping up bundle version"
    env.newInfoFile = bumpVersionXmlFile()
    // sh '''
    // cd /tmp/ios-enterprise-app
    // git checkout -b ${branch_name}
    // echo ${env.newInfoFile} | tee /enterpriseDummy/Info.plist
    // git add enterpriseDummy/Info.plist
    // git commit -m "Automated commit: ${message}"
    // git push origin ${branch_name}
    // set +x
    // response=$(curl -X POST -H "Accept: application/vnd.github.v3+json" https://${GIT_TOKEN}@api.github.ccom/repos/browserstack/ios-enterprise-app \
    // /pulls -d "$(cat <<EOF
    // {
    //   "base": "master",
    //   "head": "${branch_name}",
    //   "title": "${message}",
    //   "body": "Created by provision profile rotation job"
    // }
    // EOF
    // )" )
    //   echo $response
    //  '''
}