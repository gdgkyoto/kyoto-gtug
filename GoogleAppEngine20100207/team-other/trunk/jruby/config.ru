require 'appengine-rack'
AppEngine::Rack.configure_app(          
    :application => "tunagatta",           
    :precompilation_enabled => true,
    :version => "1")

require 'app.rb'

configure :development do
  class Sinatra::Reloader < ::Rack::Reloader
    def safe_load(file, mtime, stderr = $stderr)
      if file == Sinatra::Application.app_file 
        ::Sinatra::Application.reset!
      end
      super
    end
  end
  use Sinatra::Reloader
end

run Sinatra::Application
