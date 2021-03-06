

def getXmlParser() {
  def parser = new XmlParser()
  parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
  parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
  return parser
}

def bumpVersionXmlFile() {
  def parser = getXmlParser()
  def xmlFile = new File('/Users/ritik/testJenkinsPipeline/Info.plist')
  def parse = parser.parse(xmlFile)
  String newVersion = String.valueOf(Float.valueOf(parse.dict.string[7].text()) + 1.0)
  parse.dict.string[7].value = newVersion
  parse.dict.string[8].value = newVersion
  return XmlUtil.serialize(parse)
}

def bump_bundle_version_and_create_pr_to_ios_enterprise_app() {
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

return this