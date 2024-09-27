document.addEventListener("DOMContentLoaded", function () {
  document.querySelectorAll(".post-input").forEach((input) => {
    input.addEventListener("input", function () {
      const tweetBtn =
        this.parentElement.parentElement.querySelector(".tweet-btn");
      if (this.value.trim() !== "") {
        // If input is not empty, enable the post button
        tweetBtn.removeAttribute("disabled");
      } else {
        // If input is empty, disable the post button
        tweetBtn.setAttribute("disabled", "disabled");
      }
    });
  });
  const TweetButton = document.querySelector(".tweet-btn");
  const PostsContainer = document.querySelector(".posts");

  TweetButton.addEventListener("click", function () {
    const PostInput = document.querySelector(".post-input");
    const PostContent = PostInput.value.trim();
    // adding new post
    if (PostContent !== "") {
      const newPost = document.createElement("div");
      newPost.classList.add("newpost");
      newPost.innerHTML = `
                <div class="postinfo">
                    <div class="profileimage">
                        <img src="profiless.png" alt="" />
                    </div>
                    <div class="postdata">
                        <div class="userinfo">
                            <div class="name">Nitesh Gupta</div>
                            <div class="time">@nit_hck - 1s</div>
                            <div class="moreinfo">
                                <img src="moreinfo.png" alt="" />
                            </div>
                        </div>
                        <div class="postcontent">
                            <div class="line1">${PostContent}</div>
                        </div>
                    </div>
                </div>
                <div class="post-icons">
                    <div class="postingicons">
                        <img src="comment.png" alt="" />
                    </div>
                    <div class="postingicons">
                        <img src="repost.png" alt="" />
                    </div>
                    <div class="postingicons">
                        <button class="like-post"><img src="like.png" alt="like" /></button>
                        <button class="unlike-post" style="display: none;"><img src="unlike.png" alt="unlike" /></button>
                        <span class="likes-count"></span>
                    </div>
                    <div class="postingicons">
                        <img src="insight.png" alt="" />
                    </div>
                    <div class="postingicons">
                        <img src="save.png" alt="" />
                    </div>
                    <div class="postingicons">
                        <img src="export.png" alt="" />
                    </div>
                </div>
            `;

      PostsContainer.appendChild(newPost);
      PostInput.value = "";
      TweetButton.disabled = true;
    }
  });

  // Add event listeners for like and unlike buttons
  document.addEventListener("click", function (event) {
    if (event.target.closest(".like-post img")) {
      const post = event.target.closest(".newpost");
      const likesCount = post.querySelector(".likes-count");
      const likeButton = post.querySelector(".like-post");
      const unlikeButton = post.querySelector(".unlike-post");

      let currentLikes = parseInt(likesCount.textContent) || 0;
      if (likeButton.querySelector("img").getAttribute("src") === "like.png") {
        likeButton.querySelector("img").setAttribute("src", "unlike.png");
        currentLikes++;
      } else {
        likeButton.querySelector("img").setAttribute("src", "like.png");
        currentLikes--;
      }
      likesCount.textContent = currentLikes > 0 ? currentLikes : "";
    }
  });
});
document.addEventListener("DOMContentLoaded", function () {
  const floatingButton = document.querySelector(".floating-tweet-box-icon");
  const profileImage = document.querySelector(".topnavbar img");
  const mobileNavigation = document.querySelector(".navigationmobile");
  const tweetBox = document.querySelector(".tweet-box");
  const tweetSection = document.querySelector(".tweetsection");

  let isTweetSectionVisible = false;

  floatingButton.addEventListener("click", function () {
    if (isTweetSectionVisible) {
      tweetSection.style.display = "none";
    } else {
      tweetSection.style.display = "flex";
    }
    isTweetSectionVisible = !isTweetSectionVisible;
  });
  let isTweetBoxVisible = false;
  let isMobileNavigationVisible = false;

  floatingButton.addEventListener("click", function () {
    if (isTweetBoxVisible) {
      tweetBox.style.display = "none";
    } else {
      tweetBox.style.display = "flex";
    }
    isTweetBoxVisible = !isTweetBoxVisible;
  });

  profileImage.addEventListener("click", function () {
    if (isMobileNavigationVisible) {
      mobileNavigation.style.display = "none"; // Hide mobile navigation if already visible
    } else {
      mobileNavigation.style.display = "flex"; // Show mobile navigation if not visible
    }
    isMobileNavigationVisible = !isMobileNavigationVisible; // Toggle visibility flag
  });
});
