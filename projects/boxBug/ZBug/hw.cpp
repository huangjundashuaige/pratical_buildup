
#include <iostream> 
#include <vector> 
#include<string>
using namespace std; 

class String
{
public:
	String(const string s)
	{
		for(int i=0;s[i]!=0;i++)
		{
			this->v.push_back(s[i]);
		}
	}
	String()
	{
		
	}
	String(const char * s)
	{
		for(int i=0;s[i]!=0;i++)
		{
			this->v.push_back(s[i]);
		}
	}
	int size()
	{
		return v.size();
	}
	char & operator[](int index)
	{
		if(index > v.size())
			return this->v[0];
		else if(index == v.size())
		{
			v.push_back(' ');
			return v[v.size()-1];
		}
		else
			return v[index];
	}
	char operator[](int index) const
	{
		if(index >= this->v.size())
		{
			return ' ';
		}
		else
		{
			return this->v[index];
		}
	}
	String & operator +=(const String s)
	{
		for(int i=0;i<s.v.size();i++)
			(*this)[this->size()]=s.v[i];
		return *this;
	}
	void replace(const String before,const String after)
	{
		//for(;replace_single(before,after););
		replace_single(before,after);
	}
	bool replace_single(const String before,const String after)
	{
		vector<char> last_string;
		vector<char> temp;
		int index = find(before);
		if(index!=-1)
		{
			for(int i=index+before.v.size();i<this->size();i++)
			{
				last_string.push_back(this->v[i]);
			}
			for(int i=0;i<index;i++)
			{
				temp.push_back(this->v[i]);	
			}
			for(int i=0;i<after.v.size();i++)
			{
				temp.push_back(after[i]);
			}
			for(int i=0;i<last_string.size();i++)
			{
				temp.push_back(last_string[i]);
			}
			this->v.clear();
			for(int i=0;i<temp.size();i++)
				this->v.push_back(temp[i]);
			return true;
		}
		else
			return false;
	}
	int find(String target)
	{
		for(int i=0;i<this->size()-target.size();i++)
		{
			int flag=1;
			for(int j=0;j<target.size();j++)
			{
				if(this->v[i+j]!=target[j])
				{
					flag=0;
					break;
				}
			}
			if(flag==0)
				continue;
			else
				return i;
		}
		return -1;
	}
	String  trim()
	{
		String s;
		int end_index=this->size()-1;
		for(int i=end_index;i >=0; i-- )
		{
			if(this->v[i]!=' ')
			{
				end_index=i;
				break;
			}
		}
		int begin_index = 0;
		for(int i=0;i < this->size();i++)
		{
			if(this->v[i]!= ' ')
			{
				begin_index = i;
				break;
			}
		}
		for(int i=begin_index;i<=end_index;i++)
		{
			s[s.size()] = this->v[i];
		}
		return s;
	}
	vector<String> split(char c)
	{
		vector<String> res;
		String s;
		for(int i=0;i<this->size();i++)
		{
			if(this->v[i]==c)
			{
				if(s.size()!= 0 )
				{
					res.push_back(s);		
				}
			}
			else
			{
				s.v.push_back(this->v[i]);
			}
		}
		if(s.size()!=0)
			res.push_back(s);
		return res;
	}
    String & operator =(const String s)
	{
        this->v=s.v;
		return *this;
    }
	vector<char> v;
};

ostream & operator << (ostream & os,String s)
{
	for(int i=0;i<s.size();i++)
	{
		cout<<s.v[i];
	}
	return os;
}


 
int main() { 
    String text = "Hello, "; 
    text[text.size()] = 'I'; 
    text += " am Mike."; 
    cout << text << endl; // Hello, I am Mike. 
    text.replace("Hello", "Hi"); 
    cout << text << endl; // Hi, I am Mike. 
 
    text.replace("Mike", "Steven"); 
    cout << text << endl; // Hi, I am Steven. 
     
    String text2 = "  ABCD   "; 
    cout << "[" << text2.trim() << "]\n"; // [ABCD] 
     
    vector<String> words = text.split(','); 
    for (int i = 0; i < words.size(); ++ i) { 
        cout << words[i].trim() << endl; 
    } 
    // Hi 
    // I am Steven. 
} 