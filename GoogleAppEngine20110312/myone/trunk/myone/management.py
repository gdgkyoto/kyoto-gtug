# -*- coding: utf-8 -*-

from google.appengine.ext import db

from kay.management.utils import (
  print_status, create_db_manage_script
)
from myone.models import Competition

competitions = [
  [u'Keyboard早打ち',
	u'パソコンキーボードで"I am a programer."と入力しその時間を競う',
  ],
  [u'携帯早打ち',
	u'携帯で"次の文を英訳してください。"と入力しその時間を競う',
  ],
]

def create_competition():
  entities = []
  for item in competitions:
    entities.append(Competition(name=item[0],detail=item[1]))
  db.put(entities)
  print_status("Competitions are created successfully.")

def delete_competition():
  db.delete(Competition.all().fetch(100))
  print_status("Competitions are deleted successfully.")

action_create_competitions = create_db_manage_script(
  main_func=create_competition, clean_func=delete_competition,
  description="Create 'Competition' entities")
