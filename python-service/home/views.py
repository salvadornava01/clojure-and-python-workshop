from django.shortcuts import render
from django.http import HttpResponse
import requests

# Create your views here.
def home_view(request):
    r = requests.get('http://localhost:8081/hello')
    json_response = r.json()
    page_title_from_clojure = json_response['title']
    articles_from_clojure = json_response['articles']
    context = {'page_title': page_title_from_clojure,
               'articles': articles_from_clojure}
    return render(request, 'home.html', context)