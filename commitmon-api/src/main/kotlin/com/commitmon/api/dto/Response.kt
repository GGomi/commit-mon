package com.commitmon.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
class CommitResponse(
    @JsonProperty("total_count")
    var totalCount: Int,

    @JsonProperty("incomplete_results")
    var incompleteResults: Boolean,

    @JsonProperty("items")
    var items: List<Item> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Item(
    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("sha")
    var sha: String? = null,

    @JsonProperty("node_id")
    var nodeId: String? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("comments_url")
    var commentsUrl: String? = null,

    @JsonProperty("commit")
    var commit: Commit,

    @JsonProperty("author")
    var author: AnotherAuthor? = null,

    @JsonProperty("committer")
    var committer: AnotherCommitter? = null,

    @JsonProperty("parents")
    var parents: List<Parent>? = null,

    @JsonProperty("repository")
    var repository: Repository? = null,

    @JsonProperty("score")
    var score: Double? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Commit(
    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("author")
    var author: Author,

    @JsonProperty("committer")
    var committer: Committer,

    @JsonProperty("message")
    var message: String? = null,

    @JsonProperty("tree")
    var tree: Tree? = null,

    @JsonProperty("comment_count")
    var commentCount: Int? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Committer(
    @JsonProperty("date")
    var date: String? = null,

    @JsonProperty("name")
    var name: String? = null,

    @JsonProperty("email")
    var email: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Owner(
    @JsonProperty("login")
    var login: String? = null,

    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("node_id")
    var nodeId: String? = null,

    @JsonProperty("avatar_url")
    var avatarUrl: String? = null,

    @JsonProperty("gravatar_id")
    var gravatarId: String? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("followers_url")
    var followersUrl: String? = null,

    @JsonProperty("following_url")
    var followingUrl: String? = null,

    @JsonProperty("gists_url")
    var gistsUrl: String? = null,

    @JsonProperty("starred_url")
    var starredUrl: String? = null,

    @JsonProperty("subscriptions_url")
    var subscriptionsUrl: String? = null,

    @JsonProperty("organizations_url")
    var organizationsUrl: String? = null,

    @JsonProperty("repos_url")
    var reposUrl: String? = null,

    @JsonProperty("events_url")
    var eventsUrl: String? = null,

    @JsonProperty("received_events_url")
    var receivedEventsUrl: String? = null,

    @JsonProperty("type")
    var type: String? = null,

    @JsonProperty("site_admin")
    var siteAdmin: Boolean? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Parent(
    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("sha")
    var sha: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Repository(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("node_id")
    var nodeId: String? = null,

    @JsonProperty("name")
    var name: String? = null,

    @JsonProperty("full_name")
    var fullName: String? = null,

    @JsonProperty("private")
    var _private: Boolean? = null,

    @JsonProperty("owner")
    var owner: Owner? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("description")
    var description: Any? = null,

    @JsonProperty("fork")
    var fork: Boolean? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("forks_url")
    var forksUrl: String? = null,

    @JsonProperty("keys_url")
    var keysUrl: String? = null,

    @JsonProperty("collaborators_url")
    var collaboratorsUrl: String? = null,

    @JsonProperty("teams_url")
    var teamsUrl: String? = null,

    @JsonProperty("hooks_url")
    var hooksUrl: String? = null,

    @JsonProperty("issue_events_url")
    var issueEventsUrl: String? = null,

    @JsonProperty("events_url")
    var eventsUrl: String? = null,

    @JsonProperty("assignees_url")
    var assigneesUrl: String? = null,

    @JsonProperty("branches_url")
    var branchesUrl: String? = null,

    @JsonProperty("tags_url")
    var tagsUrl: String? = null,

    @JsonProperty("blobs_url")
    var blobsUrl: String? = null,

    @JsonProperty("git_tags_url")
    var gitTagsUrl: String? = null,

    @JsonProperty("git_refs_url")
    var gitRefsUrl: String? = null,

    @JsonProperty("trees_url")
    var treesUrl: String? = null,

    @JsonProperty("statuses_url")
    var statusesUrl: String? = null,

    @JsonProperty("languages_url")
    var languagesUrl: String? = null,

    @JsonProperty("stargazers_url")
    var stargazersUrl: String? = null,

    @JsonProperty("contributors_url")
    var contributorsUrl: String? = null,

    @JsonProperty("subscribers_url")
    var subscribersUrl: String? = null,

    @JsonProperty("subscription_url")
    var subscriptionUrl: String? = null,

    @JsonProperty("commits_url")
    var commitsUrl: String? = null,

    @JsonProperty("git_commits_url")
    var gitCommitsUrl: String? = null,

    @JsonProperty("comments_url")
    var commentsUrl: String? = null,

    @JsonProperty("issue_comment_url")
    var issueCommentUrl: String? = null,

    @JsonProperty("contents_url")
    var contentsUrl: String? = null,

    @JsonProperty("compare_url")
    var compareUrl: String? = null,

    @JsonProperty("merges_url")
    var mergesUrl: String? = null,

    @JsonProperty("archive_url")
    var archiveUrl: String? = null,

    @JsonProperty("downloads_url")
    var downloadsUrl: String? = null,

    @JsonProperty("issues_url")
    var issuesUrl: String? = null,

    @JsonProperty("pulls_url")
    var pullsUrl: String? = null,

    @JsonProperty("milestones_url")
    var milestonesUrl: String? = null,

    @JsonProperty("notifications_url")
    var notificationsUrl: String? = null,

    @JsonProperty("labels_url")
    var labelsUrl: String? = null,

    @JsonProperty("releases_url")
    var releasesUrl: String? = null,

    @JsonProperty("deployments_url")
    var deploymentsUrl: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
class Tree(
    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("sha")
    var sha: String? = null
)

class AnotherAuthor(
    @JsonProperty("login")
    var login: String? = null,

    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("node_id")
    var nodeId: String? = null,

    @JsonProperty("avatar_url")
    var avatarUrl: String? = null,

    @JsonProperty("gravatar_id")
    var gravatarId: String? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("followers_url")
    var followersUrl: String? = null,

    @JsonProperty("following_url")
    var followingUrl: String? = null,

    @JsonProperty("gists_url")
    var gistsUrl: String? = null,

    @JsonProperty("starred_url")
    var starredUrl: String? = null,

    @JsonProperty("subscriptions_url")
    var subscriptionsUrl: String? = null,

    @JsonProperty("organizations_url")
    var organizationsUrl: String? = null,

    @JsonProperty("repos_url")
    var reposUrl: String? = null,

    @JsonProperty("events_url")
    var eventsUrl: String? = null,

    @JsonProperty("received_events_url")
    var receivedEventsUrl: String? = null,

    @JsonProperty("type")
    var type: String? = null,

    @JsonProperty("site_admin")
    var siteAdmin: Boolean? = null
)

class Author(
    @JsonProperty("date")
    var date: String,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("email")
    var email: String
)

class AnotherCommitter(
    @JsonProperty("login")
    var login: String? = null,

    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("node_id")
    var nodeId: String? = null,

    @JsonProperty("avatar_url")
    var avatarUrl: String? = null,

    @JsonProperty("gravatar_id")
    var gravatarId: String? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("html_url")
    var htmlUrl: String? = null,

    @JsonProperty("followers_url")
    var followersUrl: String? = null,

    @JsonProperty("following_url")
    var followingUrl: String? = null,

    @JsonProperty("gists_url")
    var gistsUrl: String? = null,

    @JsonProperty("starred_url")
    var starredUrl: String? = null,

    @JsonProperty("subscriptions_url")
    var subscriptionsUrl: String? = null,

    @JsonProperty("organizations_url")
    var organizationsUrl: String? = null,

    @JsonProperty("repos_url")
    var reposUrl: String? = null,

    @JsonProperty("events_url")
    var eventsUrl: String? = null,

    @JsonProperty("received_events_url")
    var receivedEventsUrl: String? = null,

    @JsonProperty("type")
    var type: String? = null,

    @JsonProperty("site_admin")
    var siteAdmin: Boolean? = null
)