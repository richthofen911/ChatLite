import tornado.ioloop
import tornado.web
import urllib2
import urllib
import re
import json
import tornado.httpclient

url_signup = "https://admin.firebase.com/account"
url_login_prefix = 'https://admin.firebase.com/account/login?email='
url_login_suffix = '&password=qwer1234&rememberMe=true'
url_newapp_prefix = 'https://admin.firebase.com/firebase/chatlite'


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        email = self.get_argument('email')
        print (email)
        params_signup = urllib.urlencode({'email': email, 'password': 'qwer1234', 'referrer': 'https://www.google.ca/'})

        #res_signup = hit_url_params(url_signup, params_signup)
        http_client = tornado.httpclient.HTTPClient()
        try:            
            #sign up done !
            response = http_client.fetch('https://admin.firebase.com/account', method = 'POST', headers = None, body = params_signup)
            print (response.body)
            try:
                response = http_client.fetch(url_login_prefix + email + url_login_suffix)
                response_login_data = json.loads(response.body)
                token = response_login_data['adminToken']
                print (token)
                params_newapp = urllib.urlencode({'token': token, 'appName': 'clqq' + email[0: 5]})
                print ('new app: clqq' + email[0: 5])
                try:
                    response = http_client.fetch(url_newapp_prefix + email[0: 9], method = 'POST', headers = None, body = params_newapp)
                    print (response.body)
                except tornado.httpclient.HTTPError as e:
                    print ('Error: ' + str(e))
            except tornado.httpclient.HTTPError as e:
                print ('Error: ' + str(e))
        except tornado.httpclient.HTTPError as e:
            print ('Error: ' + str(e))
        http_client.close()

application = tornado.web.Application([
    (r"/", MainHandler),
])

if __name__ == "__main__":
    application.listen(8888)
    tornado.ioloop.IOLoop.instance().start()
