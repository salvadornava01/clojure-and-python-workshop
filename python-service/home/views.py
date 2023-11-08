from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def home_view(request):
    context = {'hello': 'hello'}
    return render(request, "home.html", context)