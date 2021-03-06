# Ready for Production

> Kotlin / Dagger2 / Rx  
> and  
> Android / Retrofit2 / OkHttp3 / Mockito

![screenshots][screenshots]


## Slide

- :kr: [Ready for Production][deck]


## Stack

- [Kotlin][kotlin]
- [Dagger 2][dagger2]
- [RxAndroid][rx-android]
- [Android][android]
- [Retrofit 2][retrofit]
- [OkHttp 3][okhttp]
- [Mockito][mockito]


### Keywords

- `let`
    - [`RepoAdapter.kt`](https://goo.gl/HmcXz2)
- `run`
    - [`MainActivity.kt`](https://goo.gl/42XJbm)
    - [`RepoViewHolder.kt`](https://goo.gl/4yfL0W)
- `apply`
    - [`ApiModule.kt`](https://goo.gl/JLsjY7)
    - [`RepoAdapter.kt`](https://goo.gl/S4FRNx)
- `data`
    - [`Repository.kt`](https://goo.gl/Je8X8b)
- `lazy`
    - [`MainActivkty.kt`](https://goo.gl/dmrO3C)
- `lateinit`
    - [`MainActivkty.kt`](https://goo.gl/9U6B1u)
    - [`MainPresenterTest.kt`](https://goo.gl/RKpniC)

#### Extensions

- [`ActivityExt`](https://goo.gl/iAbFzm)
- [`ImageViewExt`](https://goo.gl/tQYWBR)
- [`StringExt`](https://goo.gl/XYwBC0)


## Usage

### Prerequisite

Set your github properties to your `local.properties`

```sh
github.token="<YOUR_GITHUB_TOKEN>" # See https://github.com/settings/tokens
github.user="<YOUR_GITHUB_ID>"
```

### Test

```sh
$ ./gradlew test
```


## References

- :kr: [Data class and Equality](http://goo.gl/YeoDXk)
- :kr: [Function Literals with Receiver](http://goo.gl/1bvmJ3)
- :kr: [let, with, run, apply, use(using)](http://goo.gl/fZaytO)


## License

Apache 2.0 © [Jaewe Heo](http://import.re)











[screenshots]: https://cloud.githubusercontent.com/assets/1744446/13405413/9d3ad566-df61-11e5-9add-d4cebb275fe9.png
[deck]: https://speakerdeck.com/importre/ready-for-production
[kotlin]: https://kotlinlang.org/
[dagger2]: https://github.com/google/dagger
[rx-android]: https://github.com/ReactiveX/RxAndroid
[android]: http://developer.android.com/
[retrofit]: https://github.com/square/retrofit
[okhttp]: https://github.com/square/okhttp
[mockito]: https://github.com/mockito/mockito
