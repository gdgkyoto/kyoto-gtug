from google.appengine.ext import db
from ..geo.geomodel import GeoModel
from ..geo import geotypes
from user import User


class Location(GeoModel):
  user_id = db.IntegerProperty()
  created = db.DateTimeProperty(auto_now_add=True)
  updated = db.DateTimeProperty(auto_now_add=True)

  @classmethod
  def find_in_area(cls, area, limit=20, user_id=None):
    query = Location.all().order("-created")
    if user_id != None:
      user = User.get(long(user_id)) 
      query.filter("user_id =", user_id)
    return Location.bounding_box_fetch(query,
                                      geotypes.Box(area[0], area[1], area[2], area[3]),
                                      max_results=limit)
 
  @classmethod
  def find_in_circle(cls, center, radius, limit=20):
    return Location.proximity_fetch(Location.all().order("-created"),
                                    center,  # Or db.GeoPt
                                    max_results=limit,
                                    max_distance=radius)
