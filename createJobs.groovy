pipelineJob('pipelineJob') {
    definition {
        cps {
            script(readFileFromWorkspace('pipelineJob.groovy'))
            sandbox()
        }
    }
}
pipelineJob('certgen-job') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url = 'https://github.com/rojberr/certgen'
                    }
                    branch 'master'
                }
            }
        }
    }
}